package com.core.nicetry.modules.users.services.activitiesUser;

import com.core.nicetry.modules.users.services.activitiesUser.dto.ActivitiesUserResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuário")
@RestController
@RequestMapping("/user/actives")
public class ActivitiesUserController {

  @Autowired
  ActivitiesUserService service;

  @ApiOperation("Endpoint responsável listar todas as atividades do usuário.")
  @Tag(name = "Usuário")
  @GetMapping()
  @ResponseBody
  public ResponseEntity<List<ActivitiesUserResponseDTO>> handle(){

    List<ActivitiesUserResponseDTO> list = service.execute();

    return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
  }
}
