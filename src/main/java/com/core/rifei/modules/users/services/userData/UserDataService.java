package com.core.rifei.modules.users.services.userData;

import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.modules.users.services.userData.dto.UserDataDTO;
import com.core.rifei.security.context.SetUserService;
import com.core.rifei.utils.MaskCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  SetUserService setUserService;

  @Autowired
  MaskCPF maskCPF;


  public UserDataDTO execute() {

    Users userLog =  setUserService.execute();

    Users user = usersRepository.findById(userLog.getId()).orElse(null);
    if(user == null){
      throw new Error("UserNotFound");
    }

    UserDataDTO response = new UserDataDTO();
    response.setEmail(user.getLogin());
    response.setName(user.getName());
    response.setId(user.getId());
    response.setObservation(user.getObservation());
    response.setPhoneNumber(user.getPhoneNumber());
    response.setEmail_verify(user.isStatus());
    if(user.getCpf() != null){
      response.setCpf(maskCPF.execute(user.getCpf()));
    }

    return response;
  }
}
