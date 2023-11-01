package com.core.rifei.modules.users.services.changeData;

import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.modules.users.services.changeData.dto.ChengeDataUserDTO;
import com.core.rifei.security.context.GetUserService;
import com.core.rifei.utils.CpfAndCnpjValidation;
import com.core.rifei.utils.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChengeDataUserService {


  @Autowired
  UsersRepository usersRepository;


  @Autowired
  GetUserService setUserService;

  @Autowired
  PasswordValidation passwordValidation;

  @Autowired
  CpfAndCnpjValidation cpfAndCnpjValidation;

  public void execute(ChengeDataUserDTO data) {

    Users userLogged = setUserService.execute();

    Users users = usersRepository.findById(userLogged.getId()).orElse(null);
    if(users == null){
      throw new Error("UserNotFound");
    }

    if(data.getName() != null && !data.getName().isEmpty()){
      users.setName(data.getName());
    }

    if(data.getNumberPhone() != null && !data.getNumberPhone().isEmpty()){
      users.setPhoneNumber(data.getNumberPhone());
    }

    if(data.getObservation() != null && !data.getObservation().isEmpty()){
      users.setObservation(data.getObservation());
    }

    if(data.getCpf() != null && !data.getCpf().isEmpty()){
      if(cpfAndCnpjValidation.isCPF(data.getCpf())){
        users.setCpf(data.getCpf());
      }else{
        throw new Error("CPFInvalid");
      }
    }

    usersRepository.saveAndFlush(users);
  }
}
