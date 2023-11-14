package com.core.nicetry.modules.campaigns.services.listResultDrawCampaign;


import com.core.nicetry.modules.campaigns.services.listResultDrawCampaign.dtos.ListResultWiner;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Tag(name = "Campanha")
@RestController
@RequestMapping("/campaign/result/{idCampaign}")
public class ListResultCampaignController {

  @Autowired
  ListResultCampaignService service;


  @ApiOperation("Endpoint responsável por listar o detalhes do resultado da campanha.")
  @Tag(name = "Campanha")
  @GetMapping
  @ResponseBody
  public ResponseEntity<List<ListResultWiner>> handle(@PathVariable("idCampaign") UUID idCampaign) {

    List<ListResultWiner> response = service.execute(idCampaign);

    return new ResponseEntity<>(response,HttpStatus.OK);
  }
}
