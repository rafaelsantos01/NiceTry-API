package com.core.nicetry.modules.users.services.solicitationRecoveryPassword;


import com.core.nicetry.modules.users.entityes.ReceveryPassword;
import com.core.nicetry.modules.users.entityes.Users;
import com.core.nicetry.modules.users.repository.ReceveryPasswordRepository;
import com.core.nicetry.modules.users.repository.UsersRepository;
import com.core.nicetry.shared.SendEmail.SendEmailService;
import com.core.nicetry.shared.SendEmail.ENUM.TEMPLATETYPE;
import com.core.nicetry.shared.SendEmail.dto.SendEmailServiceDTO;
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
