package com.core.nicetry.modules.users.services.solicitationRecoveryPassword;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuário")
@RestController
@RequestMapping("/auth/solicitation/recovery/{email}")
public class SolicitationRecoveryPasswordController {


  @Autowired
  SolicitationRecoveryPasswordService service;


  @ApiOperation("Endpoint responsável por solicitar uma recuperação de senha.")
  @Tag(name = "Usuário")
  @PutMapping()
  @ResponseBody
  public ResponseEntity<Void> handle(@PathVariable("email") String email){

    service.execute(email);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
