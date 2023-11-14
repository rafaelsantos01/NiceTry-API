package com.core.nicetry.modules.users.services.confirmationEmailUser.resendConfirmationEmail;

import com.core.nicetry.modules.users.services.confirmationEmailUser.resendConfirmationEmail.dto.ResendConfirmationEmailDTO;
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
@RequestMapping("/user/confirmEmail")
public class ResendConfirmationEmailController {


  @Autowired
  ResendConfirmationEmailService resendConfirmationEmailService;

  @ApiOperation("Endpoint responsável reenviar a confirmação do email do cliente cadastrado.")
  @Tag(name = "Usuário")
  @PutMapping()
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_USER')")

  public ResponseEntity<Void> handle(@RequestBody @Valid ResendConfirmationEmailDTO data){

    resendConfirmationEmailService.execute(data);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
