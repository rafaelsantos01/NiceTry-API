package com.core.nicetry.modules.dashboard.services.lastAcquisition;

import com.core.nicetry.modules.dashboard.services.lastAcquisition.dto.LastAcquisitionDTO;
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
@RequestMapping("/admin/last-acquisition")
public class LastAcquisitionController {


  @Autowired
  LastAcquisitionService service;


  @ApiOperation("Endpoint responsável por mostrar as ultimas 5 compras .")
  @Tag(name = "Dashboard Administrador")
  @GetMapping
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<LastAcquisitionDTO>> hanle(){

    List<LastAcquisitionDTO> respons = service.execute();

    return  new ResponseEntity<>(respons, HttpStatus.OK);
  }


}
