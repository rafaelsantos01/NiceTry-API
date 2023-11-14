package com.core.nicetry.modules.users.services.Authentication.refresh;

import com.core.nicetry.modules.users.entityes.Users;
import com.core.nicetry.modules.users.repository.UsersRepository;
import com.core.nicetry.modules.users.services.Authentication.dto.LoginResponseDTO;
import com.core.nicetry.security.TokenService;
import com.core.nicetry.security.context.GetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {


  @Autowired
  private GetUserService userService;

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
