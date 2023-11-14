package com.core.nicetry.modules.dashboard.services.listAllUsers;

import com.core.nicetry.modules.dashboard.services.listAllUsers.dto.ListAllUsersDTO;
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

import java.util.List;

@Tag(name = "Dashboard Administrador")
@RestController
@RequestMapping("/admin/users")
public class ListAllUsersController {


  @Autowired
  ListAllUsersService listAllUsersService;


  @ApiOperation("Endpoint responsável por mostrar todos os usuários cadastrados no sistema.")
  @Tag(name = "Dashboard Administrador")
  @GetMapping
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<ListAllUsersDTO>> handle(){

    List<ListAllUsersDTO> respon = listAllUsersService.execute();

    return new ResponseEntity<>(respon, HttpStatus.OK);
  }
}
