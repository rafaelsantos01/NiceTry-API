package com.core.rifei.modules.users.services.userData;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Usuário")
@RestController
@RequestMapping("user/profile")
public class UserDataController {

  @Autowired
  UserDataService userDataService;


  @ApiOperation("Endpoint responsável por mostrar os dados do usuário logado.")
  @Tag(name = "Usuário")
  @GetMapping
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<UserDataDTO> handle(){
    UserDataDTO res = userDataService.execute();

    return new ResponseEntity<>(res, HttpStatus.OK);
  }

}
