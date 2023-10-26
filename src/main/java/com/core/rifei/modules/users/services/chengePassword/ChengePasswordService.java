package com.core.rifei.modules.users.services.chengePassword;

import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.modules.users.services.chengePassword.dto.ChangePasswordDTO;
import com.core.rifei.security.context.SetUserService;
import com.core.rifei.shared.SendEmail.SendEmailService;
import com.core.rifei.shared.SendEmail.ENUM.TEMPLATETYPE;
import com.core.rifei.shared.SendEmail.dto.SendEmailServiceDTO;
import com.core.rifei.utils.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChengePasswordService {


  @Autowired
  UsersRepository usersRepository;

  @Autowired
  SetUserService setUserService;

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
