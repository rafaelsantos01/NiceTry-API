package com.core.rifei.modules.users.services.solicitationRecoveryPassword;

import com.core.rifei.modules.users.services.confirmationEmailUser.resendConfirmationEmail.dto.ResendConfirmationEmailDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
