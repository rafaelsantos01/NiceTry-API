package com.core.nicetry.modules.campaigns.services.searchAllCampaigns;

import com.core.nicetry.modules.campaigns.services.searchAllCampaigns.dto.SearchAllCampaignsDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Campanha")
@RestController
@RequestMapping("/campaigns/{status}")
public class SearchAllCampaignsController {

  @Autowired
  private SearchAllCampaignsService service;


  @ApiOperation("Endpoint responsável por listar as campanhas ativas ou inativas.")
  @Tag(name = "Campanha")
  @GetMapping
  @ResponseBody
  public ResponseEntity<List<SearchAllCampaignsDTO>> handle(@PathVariable(value = "status") boolean status){

    List<SearchAllCampaignsDTO> campaigns = service.execute(status);

    return new ResponseEntity<>(campaigns, HttpStatus.OK);
  }
}
