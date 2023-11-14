package com.core.nicetry.modules.users.services.chengePassword;


import com.core.nicetry.modules.users.services.chengePassword.dto.ChangePasswordDTO;
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
@RequestMapping("user/changePassword")
public class ChengePasswordController {

  @Autowired
  ChengePasswordService chengePasswordService;


  @ApiOperation("Endpoint responsável por alterar a senha do usuário logado.")
  @Tag(name = "Usuário")
  @PutMapping()
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<Void> handle(@RequestBody @Valid ChangePasswordDTO data){

    chengePasswordService.execute(data);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
