package com.core.nicetry.security.context;

import com.core.nicetry.modules.users.entityes.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GetUserService {


  public Users execute(){

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    Users userAuth = (Users) authentication.getPrincipal();

    Users users = new Users();
    users.setId(userAuth.getId());

    return users;
  }
}
