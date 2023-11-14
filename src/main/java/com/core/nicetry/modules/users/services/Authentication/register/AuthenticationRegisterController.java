package com.core.nicetry.modules.users.services.Authentication.register;


import com.core.nicetry.modules.users.dto.UsersDTO;
import com.core.nicetry.modules.users.services.Authentication.dto.RegisterDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Autenticação")
@RestController
@RequestMapping("auth")
public class AuthenticationRegisterController {

  @Autowired
  AuthenticationRegisterService authenticationRegisterService;

  @ApiOperation("Endpoint responsável por cadastrar um novo usuário")
  @Tag(name = "Autenticação")
  @PostMapping("/register")
  @ResponseBody
  public ResponseEntity<UsersDTO> register(@RequestBody @Valid RegisterDTO data){

    UsersDTO register = authenticationRegisterService.register(data);

    return new ResponseEntity<>(register, HttpStatus.CREATED);
  }
}
