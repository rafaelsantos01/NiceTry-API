package com.core.rifei.modules.users.services.confirmationEmailUser.resendConfirmationEmail;

import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.modules.users.services.confirmationEmailUser.resendConfirmationEmail.dto.ResendConfirmationEmailDTO;
import com.core.rifei.security.context.SetUserService;
import com.core.rifei.shared.SendEmail.SendEmailService;
import com.core.rifei.shared.SendEmail.ENUM.TEMPLATETYPE;
import com.core.rifei.shared.SendEmail.dto.SendEmailServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ResendConfirmationEmailService {
  @Autowired
  UsersRepository usersRepository;

  @Autowired
  SetUserService setUserService;


  @Autowired
  SendEmailService sendEmailService;

  @Value("${app.niceTry.domain.url}")
  private String domain;

  public void execute(ResendConfirmationEmailDTO data) {

    Users user = setUserService.execute();

    Users users = usersRepository.findById(user.getId()).orElse(null);
    if(users == null){
      throw new Error("UserNotFound");
    }

    if(users.isStatus()){
      throw new Error("confirmedEmail");
    }

    if(!users.getLogin().equals(data.getEmail())){
      UserDetails byLogin = usersRepository.findByLogin(data.getEmail());
      if(byLogin != null){
        throw new Error("UserAlreadyExists");
      }
    }
    users.setLogin(data.getEmail());
    usersRepository.saveAndFlush(users);

    try {
      SendEmailServiceDTO sendEmailServiceDTO = new SendEmailServiceDTO();
      sendEmailServiceDTO.setUserName(users.getName());
      sendEmailServiceDTO.setUserMail(users.getUsername());
      sendEmailServiceDTO.setLink(domain+"/confirmation/"+ users.getId());
      sendEmailServiceDTO.setTemplateType(TEMPLATETYPE.RESEND_CONFIRMATION_EMAIL.getKey());

      sendEmailService.SendEmail(sendEmailServiceDTO);
    }catch (Exception e){
      throw new Error("ErrorSendEmail");
    }
  }
}
