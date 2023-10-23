package com.core.rifei.modules.users.services.confirmationEmailUser.confirmationEmail;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Autenticação")
@RestController
@RequestMapping("auth/confirmation/{id_user}")
public class ConfirmationEmailUserController {


  @Autowired
  ConfirmationEmailUserService confirmationEmailUserService;

  @ApiOperation("Endpoint responsável por confirmar o email de um usuário.")
  @Tag(name = "Autenticação")
  @PutMapping
  public ResponseEntity<Void> handle(@PathVariable("id_user") UUID id_user ){

    confirmationEmailUserService.execute(id_user);

    return  new ResponseEntity<>(HttpStatus.OK);
  }
}
