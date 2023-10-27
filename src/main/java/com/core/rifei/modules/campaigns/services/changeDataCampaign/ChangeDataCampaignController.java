package com.core.rifei.modules.campaigns.services.changeDataCampaign;

import com.core.rifei.modules.campaigns.services.changeDataCampaign.dto.ChangeDataCampaignDTO;
import com.core.rifei.modules.campaigns.services.changeStatusCampaign.ChangeStatusCampaignService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Campanha")
@RequestMapping("/admin/campaign/data/{id_campaign}")
@RestController
public class ChangeDataCampaignController {

  @Autowired
  ChangeDataCampaignService changeDataCampaignService;


  @ApiOperation("Endpoint responsável por alterar os dados da campanha.")
  @Tag(name = "Campanha")
  @PutMapping
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Void> handle(@PathVariable("id_campaign") UUID idCampaign,@RequestBody ChangeDataCampaignDTO data){

    changeDataCampaignService.execute(idCampaign,data);

    return  new ResponseEntity<>(HttpStatus.OK);
  }
}
