package com.core.rifei.modules.users.services.recoveryPassword;

import com.core.rifei.modules.users.services.recoveryPassword.dto.RecoveryPasswordDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Usuário")
@RestController
@RequestMapping("/auth/recovery")
public class RecoveryPasswordController {

  @Autowired
  RecoveryPasswordService service;


  @ApiOperation("Endpoint responsável por alterar a senha do usuário.")
  @Tag(name = "Usuário")
  @PostMapping()
  @ResponseBody
  public ResponseEntity<Void> handle(@RequestBody @Valid RecoveryPasswordDTO data){

    service.execute(data);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
