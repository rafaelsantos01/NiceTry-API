package com.core.nicetry.modules.campaigns.services.createNewCampaign;

import com.core.nicetry.modules.campaigns.dto.CampaignDTO;
import com.core.nicetry.modules.campaigns.services.createNewCampaign.dto.CreateCampaignDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Campanha")
@RestController
@RequestMapping("/campaigns")
public class CreateNewCampaignController {


  @Autowired
  CreateNewCampaignService service;


  @ApiOperation("Endpoint responsável por criar uma nova campanha.")
  @Tag(name = "Campanha")
 @PostMapping
 @ResponseBody
 @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<CampaignDTO> handle(@RequestBody @Valid CreateCampaignDTO data){

    CampaignDTO campaigns = service.execute(data);

    return new ResponseEntity<>(campaigns, HttpStatus.CREATED);
  }


}
