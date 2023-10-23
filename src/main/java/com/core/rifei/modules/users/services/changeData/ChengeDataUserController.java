package com.core.rifei.modules.users.services.changeData;


import com.core.rifei.modules.users.services.changeData.dto.ChengeDataUserDTO;
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
@RequestMapping("user/changeData")
public class ChengeDataUserController {

  @Autowired
  ChengeDataUserService chengePasswordService;


  @ApiOperation("Endpoint responsável por alterar alguns dados do usuario logado.")
  @Tag(name = "Usuário")
  @PutMapping()
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<Void> handle(@RequestBody @Valid ChengeDataUserDTO data){

    chengePasswordService.execute(data);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
