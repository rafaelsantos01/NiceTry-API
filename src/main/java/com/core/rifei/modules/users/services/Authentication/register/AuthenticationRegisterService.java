package com.core.rifei.modules.users.services.Authentication.register;

import com.core.rifei.modules.users.ENUM.UserRole;
import com.core.rifei.modules.users.dto.UsersDTO;
import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.modules.users.services.Authentication.dto.RegisterDTO;
import com.core.rifei.shared.SendEmail.SendEmailService;
import com.core.rifei.shared.SendEmail.ENUM.TEMPLATETYPE;
import com.core.rifei.shared.SendEmail.dto.SendEmailServiceDTO;
import com.core.rifei.utils.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class AuthenticationRegisterService {

  @Autowired
  UsersRepository usersRepository;


  @Autowired
  PasswordValidation passwordValidation;


  @Autowired
  SendEmailService sendEmailService;


  public UsersDTO register(RegisterDTO data){
    UsersDTO usersDTO = new UsersDTO();

    if(usersRepository.findByLogin(data.getLogin()) != null) {
        throw new Error("UserAlreadyExists");
    }

    if(!data.getPassword().equals(data.getPassword_confirmation())){
      throw new Error("PasswordsNotMatch");
    }

    if(!passwordValidation.execute(data.getPassword())){
      throw new Error("PasswordsNotMeetMinimumRequirements");
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

    Date dataAtual = new Date();
    Timestamp timestamp = new Timestamp(dataAtual.getTime());

    Users newUser = new Users();

    newUser.setName(data.getName());
    //newUser.setCpf(data.getCpf());
    newUser.setLogin(data.getLogin());
    newUser.setPassword(encryptedPassword);
    newUser.setPermission(UserRole.USER);
    newUser.setCreateDate(timestamp);
    //newUser.setObservation(data.getTradeLink());
    newUser.setStatus(false);

    Users users = usersRepository.saveAndFlush(newUser);

    usersDTO.setId(users.getId());
    usersDTO.setName(users.getName());
    usersDTO.setLogin(users.getLogin());
    usersDTO.setCpf(users.getCpf());

    try {
      SendEmailServiceDTO sendEmailServiceDTO = new SendEmailServiceDTO();
      sendEmailServiceDTO.setUserName(users.getName());
      sendEmailServiceDTO.setUserMail(users.getUsername());
      sendEmailServiceDTO.setLink("https://nicetry.com.br/confirmation/"+ users.getId());
      sendEmailServiceDTO.setTemplateType(TEMPLATETYPE.NEW_USER.getKey());

      sendEmailService.SendEmail(sendEmailServiceDTO);
    }catch (Exception e){
      throw new Error("ErrorSendEmail");
    }

    return usersDTO;
  }
}
