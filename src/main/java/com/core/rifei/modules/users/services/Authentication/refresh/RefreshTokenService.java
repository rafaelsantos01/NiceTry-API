package com.core.rifei.modules.users.services.Authentication.refresh;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.modules.users.services.Authentication.dto.LoginResponseDTO;
import com.core.rifei.security.TokenService;
import com.core.rifei.security.context.SetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {


  @Autowired
  private SetUserService userService;

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  private TokenService tokenService;

  public LoginResponseDTO handle(String token) {
    Users execute = userService.execute();

    Users users = usersRepository.findById(execute.getId()).orElse(null);

    String accessToken = tokenService.generateToken(users);
    String refreshToken = tokenService.generateRefreshToken(users);

    LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

    loginResponseDTO.setAccess_token(accessToken);
    loginResponseDTO.setRefresh_token(refreshToken);

    return loginResponseDTO;
  }
}
