package com.core.rifei.modules.users.services.solicitationRecoveryPassword;


import com.core.rifei.modules.users.entityes.ReceveryPassword;
import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.ReceveryPasswordRepository;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.shared.SendEmail.SendEmailService;
import com.core.rifei.shared.SendEmail.ENUM.TEMPLATETYPE;
import com.core.rifei.shared.SendEmail.dto.SendEmailServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class SolicitationRecoveryPasswordService {

  @Autowired
  UsersRepository usersRepository;


  @Autowired
  ReceveryPasswordRepository receveryPasswordRepository;

  @Autowired
  SendEmailService sendEmailService;


  public void execute(String email) {
    Users user =(Users) usersRepository.findByLogin(email);
    if(user != null){
      String token = "REC-" + UUID.randomUUID();
      ReceveryPassword receveryPassword = new ReceveryPassword();
      receveryPassword.setStatus(false);
      receveryPassword.setUsers(user);
      receveryPassword.setToken(token);

      try {
        SendEmailServiceDTO sendEmailServiceDTO = new SendEmailServiceDTO();
        sendEmailServiceDTO.setUserName(user.getName());
        sendEmailServiceDTO.setUserMail(user.getUsername());
        sendEmailServiceDTO.setLink("https://nicetry.com.br/auth/recovery/"+token);
        sendEmailServiceDTO.setTemplateType(TEMPLATETYPE.RECOVERY_PASSWORD.getKey());

        sendEmailService.SendEmail(sendEmailServiceDTO);
      }catch (Exception e){
        throw new Error("ErrorSendEmail");
      }

      receveryPasswordRepository.saveAndFlush(receveryPassword);
    }
  }
}
