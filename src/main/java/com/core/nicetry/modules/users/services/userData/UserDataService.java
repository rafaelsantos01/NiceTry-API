package com.core.nicetry.modules.users.services.userData;

import com.core.nicetry.modules.users.entityes.Users;
import com.core.nicetry.modules.users.repository.UsersRepository;
import com.core.nicetry.modules.users.services.userData.dto.UserDataDTO;
import com.core.nicetry.security.context.GetUserService;
import com.core.nicetry.utils.MaskCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  GetUserService setUserService;

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
