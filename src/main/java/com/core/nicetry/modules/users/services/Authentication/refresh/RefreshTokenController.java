package com.core.nicetry.modules.users.services.Authentication.refresh;

import com.core.nicetry.modules.users.services.Authentication.dto.LoginResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Autenticação")
@RestController
@RequestMapping("auth")
public class RefreshTokenController {

  @Autowired
  RefreshTokenService tokenService;

  @ApiOperation("Endpoint responsável por realizar refresh do token do usuário.")
  @Tag(name = "Autenticação")
  @PostMapping("/refresh")
  @ResponseBody
  public ResponseEntity<LoginResponseDTO> refresh(@RequestHeader("Authorization") String refreshToken){
    System.err.println("REFRESH");

    LoginResponseDTO token = tokenService.handle(refreshToken);

    return new ResponseEntity<>(token, HttpStatus.OK);
  }
}
