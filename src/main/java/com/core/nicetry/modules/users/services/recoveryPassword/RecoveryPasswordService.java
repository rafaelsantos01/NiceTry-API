package com.core.nicetry.modules.users.services.recoveryPassword;

import com.core.nicetry.modules.users.entityes.ReceveryPassword;
import com.core.nicetry.modules.users.entityes.Users;
import com.core.nicetry.modules.users.repository.ReceveryPasswordRepository;
import com.core.nicetry.modules.users.repository.UsersRepository;
import com.core.nicetry.modules.users.services.recoveryPassword.dto.RecoveryPasswordDTO;
import com.core.nicetry.shared.SendEmail.SendEmailService;
import com.core.nicetry.shared.SendEmail.ENUM.TEMPLATETYPE;
import com.core.nicetry.shared.SendEmail.dto.SendEmailServiceDTO;
import com.core.nicetry.utils.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class RecoveryPasswordService {

  @Autowired
  ReceveryPasswordRepository receveryPasswordRepository;

  @Autowired
  PasswordValidation passwordValidation;

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  SendEmailService sendEmailService;

  public void execute(RecoveryPasswordDTO data) {

    ReceveryPassword token = receveryPasswordRepository.findByTokenAndStatusFalse(data.getToken());
    if(token == null){
      throw new Error("AlreadyUsedCode");
    }

    if(!data.getPassword().equals(data.getPassword_confirmation())){
      throw new Error("PasswordsNotMatch");
    }

    if(!passwordValidation.execute(data.getPassword())){
      throw new Error("PasswordsNotMeetMinimumRequirements");
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

    Users user = token.getUsers();
    user.setPassword(encryptedPassword);


    usersRepository.saveAndFlush(user);
    token.setStatus(true);
    receveryPasswordRepository.saveAndFlush(token);

    try {
      SendEmailServiceDTO sendEmailServiceDTO = new SendEmailServiceDTO();
      sendEmailServiceDTO.setUserName(user.getName());
      sendEmailServiceDTO.setUserMail(user.getUsername());
      sendEmailServiceDTO.setTemplateType(TEMPLATETYPE.CHANGE_PASSWORD.getKey());

      sendEmailService.SendEmail(sendEmailServiceDTO);
    }catch (Exception e){
      throw new Error("ErrorSendEmail");
    }
  }
}
