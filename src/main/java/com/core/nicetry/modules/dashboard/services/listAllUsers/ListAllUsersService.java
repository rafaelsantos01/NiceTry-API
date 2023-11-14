package com.core.nicetry.modules.dashboard.services.listAllUsers;

import com.core.nicetry.modules.dashboard.services.listAllUsers.dto.ListAllUsersDTO;
import com.core.nicetry.modules.users.entityes.Users;
import com.core.nicetry.modules.users.repository.UsersRepository;
import com.core.nicetry.utils.EmailMasker;
import com.core.nicetry.utils.MaskCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListAllUsersService {

  @Autowired
  UsersRepository usersRepository;


  @Autowired
  MaskCPF maskCPF;

  @Autowired
  EmailMasker emailMasker;

  public List<ListAllUsersDTO> execute() {
    List<ListAllUsersDTO> usersDTO = new ArrayList<>();

    List<Users> users = usersRepository.findAll();
    for(Users user : users){
      ListAllUsersDTO listAllUsersDTO = new ListAllUsersDTO();
      listAllUsersDTO.setId(user.getId());
      listAllUsersDTO.setName(user.getName());
      listAllUsersDTO.setStatus(user.isStatus());
      listAllUsersDTO.setPermission(user.getPermission());
      listAllUsersDTO.setObservation(user.getObservation());
      listAllUsersDTO.setEmail(emailMasker.maskEmail(user.getLogin()));
      if(user.getCpf() != null){
        listAllUsersDTO.setCpf(maskCPF.execute(user.getCpf()));
      }

      usersDTO.add(listAllUsersDTO);
    }

    return  usersDTO;
  }
}
