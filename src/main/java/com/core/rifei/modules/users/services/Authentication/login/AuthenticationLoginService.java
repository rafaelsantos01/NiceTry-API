package com.core.rifei.modules.users.services.Authentication.login;

import com.core.rifei.modules.users.ENUM.ROLES;
import com.core.rifei.modules.users.ENUM.UserRole;
import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.modules.users.services.Authentication.dto.AuthenticationDTO;
import com.core.rifei.modules.users.services.Authentication.dto.LoginResponseDTO;
import com.core.rifei.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;

@Service
public class AuthenticationLoginService {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UsersRepository repository;

  @Autowired
  private TokenService tokenService;

//  public LoginResponseDTO handle(AuthenticationDTO data){
//    LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
//
//    try {
//      UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
//      Authentication auth = authenticationManager.authenticate(usernamePassword);
//
//      String token = tokenService.generateToken((Users) auth.getPrincipal());
//
//      loginResponseDTO.setAccess_token(token);
//      loginResponseDTO.setRefresh_token(token);
//    }catch (Exception error){
//        throw new Error("LoginFail");
//    }
//
//    return loginResponseDTO;
//  }
//}


  public LoginResponseDTO handle(AuthenticationDTO data) {
    LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

    try {
      UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
      Authentication auth = authenticationManager.authenticate(usernamePassword);

      if (auth.isAuthenticated()) {
        // Obter as permissões do usuário autenticado
        List<String> permissions = auth.getAuthorities()
          .stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.toList());


        String token = tokenService.generateToken((Users) auth.getPrincipal());

        loginResponseDTO.setAccess_token(token);
        loginResponseDTO.setRefresh_token(token);
        loginResponseDTO.setPermission(permissions);
      } else {
        throw new Error("LoginFail");
      }
    } catch (Exception error) {
      throw new Error("LoginFail");
    }

    return loginResponseDTO;
  }
}