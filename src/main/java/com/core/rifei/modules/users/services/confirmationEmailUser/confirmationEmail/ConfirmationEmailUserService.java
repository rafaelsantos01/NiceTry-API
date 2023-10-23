package com.core.rifei.modules.users.services.confirmationEmailUser.confirmationEmail;

import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConfirmationEmailUserService {


  @Autowired
  UsersRepository usersRepository;

  public void execute(UUID idUser) {

    Users users = usersRepository.findById(idUser).orElse(null);
    if(users == null){
      throw new Error("UserNotFound");
    }
    if(users.isStatus()){
      throw new Error("confirmedEmail");
    }

    users.setStatus(true);

    usersRepository.saveAndFlush(users);
  }
}
