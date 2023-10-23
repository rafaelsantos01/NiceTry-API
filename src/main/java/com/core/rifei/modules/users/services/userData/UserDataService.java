package com.core.rifei.modules.users.services.userData;

import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.security.context.SetUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDataService {

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  SetUserService setUserService;


  public UserDataDTO execute() {

    Users userLog =  setUserService.execute();

    Users user = usersRepository.findById(userLog.getId()).orElse(null);
    if(user == null){
      throw new Error("UserNotFound");
    }

    UserDataDTO response = new UserDataDTO();
    response.setEmail(user.getLogin());
    response.setName(user.getName());
    response.setCpf(user.getCpf());
    response.setId(user.getId());
    response.setTradeLink(user.getTradeLink());
    response.setPhoneNumber(user.getPhoneNumber());
    response.setEmail_verify(user.isStatus());

    return response;
  }
}
