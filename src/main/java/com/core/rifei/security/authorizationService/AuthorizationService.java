package com.core.rifei.security.authorizationService;

import com.core.rifei.modules.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {


  @Autowired
  UsersRepository usersRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user = usersRepository.findByLogin(username);

    if(user != null ){
      return user;
    }else{
      throw new UsernameNotFoundException("UserNotFound");
    }



  }
}
