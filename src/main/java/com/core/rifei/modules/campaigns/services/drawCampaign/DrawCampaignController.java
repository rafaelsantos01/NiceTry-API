package com.core.rifei.modules.campaigns.services.drawCampaign;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Tag(name = "Campanha")
@RestController
@RequestMapping("/admin/draw/campaign/{idCampaign}")
public class DrawCampaignController {

  @Autowired
  DrawCampaignService service;


  @ApiOperation("Endpoint responsável por sortear uma campanha.")
  @Tag(name = "Campanha")
  @PutMapping
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Void> handle(@PathVariable("idCampaign") UUID idCampaign){
    service.execute(idCampaign);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
