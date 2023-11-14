package com.core.nicetry.modules.users.services.chengePassword;

import com.core.nicetry.modules.users.entityes.Users;
import com.core.nicetry.modules.users.repository.UsersRepository;
import com.core.nicetry.modules.users.services.chengePassword.dto.ChangePasswordDTO;
import com.core.nicetry.security.context.GetUserService;
import com.core.nicetry.shared.SendEmail.SendEmailService;
import com.core.nicetry.shared.SendEmail.ENUM.TEMPLATETYPE;
import com.core.nicetry.shared.SendEmail.dto.SendEmailServiceDTO;
import com.core.nicetry.utils.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChengePasswordService {


  @Autowired
  UsersRepository usersRepository;

  @Autowired
  GetUserService setUserService;

  @Autowired
  PasswordValidation passwordValidation;

  @Autowired
  SendEmailService sendEmailService;

  public void execute(ChangePasswordDTO data) {

    Users userLogged = setUserService.execute();

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    Users users = usersRepository.findById(userLogged.getId()).orElse(null);
    if(users == null){
      throw new Error("UserNotFound");
    }

    if(!passwordValidation.execute(data.getNew_password())){
      throw new Error("PasswordsNotMeetMinimumRequirements");
    }

    if(!data.getNew_password().equals(data.getConfirmation_new_password())){
      throw new Error("PasswordsNotMatch");
    }

    if(!encoder.matches(data.getOld_password(), users.getPassword())){
      throw new Error("passwordOldError");
    }

    users.setPassword(encoder.encode(data.getNew_password()));

    usersRepository.saveAndFlush(users);

    SendEmailServiceDTO sendEmailServiceDTO = new SendEmailServiceDTO();
    sendEmailServiceDTO.setTemplateType(TEMPLATETYPE.CHANGE_PASSWORD.getKey());
    sendEmailServiceDTO.setUserName(users.getName());
    sendEmailServiceDTO.setUserMail(users.getUsername());
    sendEmailService.SendEmail(sendEmailServiceDTO);

  }
}
