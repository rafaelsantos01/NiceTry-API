package com.core.rifei.modules.dashboard.services.listAllUsers;

import com.core.rifei.modules.dashboard.services.listAllUsers.dto.ListAllUsersDTO;
import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.security.context.SetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListAllUsersService {

  @Autowired
  UsersRepository usersRepository;


  public List<ListAllUsersDTO> execute() {
    List<ListAllUsersDTO> usersDTO = new ArrayList<>();

    List<Users> users = usersRepository.findAll();
    for(Users user : users){
      ListAllUsersDTO listAllUsersDTO = new ListAllUsersDTO();
      listAllUsersDTO.setId(user.getId());
      listAllUsersDTO.setCpf(user.getCpf());
      listAllUsersDTO.setEmail(user.getLogin());
      listAllUsersDTO.setName(user.getName());
      listAllUsersDTO.setStatus(user.isStatus());
      listAllUsersDTO.setPermission(user.getPermission());
      listAllUsersDTO.setObservation(user.getTradeLink());

      usersDTO.add(listAllUsersDTO);
    }

    return  usersDTO;
  }
}
