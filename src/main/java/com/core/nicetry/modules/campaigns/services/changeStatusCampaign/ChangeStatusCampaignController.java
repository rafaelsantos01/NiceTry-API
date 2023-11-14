package com.core.nicetry.modules.campaigns.services.changeStatusCampaign;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Campanha")
@RequestMapping("/admin/campaign/{id_campaign}")
@RestController
public class ChangeStatusCampaignController {


  @Autowired
  ChangeStatusCampaignService service;

  @ApiOperation("Endpoint responsável por alterar o status da campanha.")
  @Tag(name = "Campanha")
  @PutMapping
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Void> handle(@PathVariable("id_campaign") UUID idCampaign){

    service.execute(idCampaign);

    return  new ResponseEntity<>(HttpStatus.OK);
  }
}

