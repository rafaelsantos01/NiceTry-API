package com.core.nicetry.modules.campaigns.services.changeStatusCampaign;


import com.core.nicetry.modules.campaigns.entityes.Campaigns;
import com.core.nicetry.modules.campaigns.repository.CampaignsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChangeStatusCampaignService {

  @Autowired
  CampaignsRepository campaignsRepository;


  public void execute(UUID idCampaign) {

    Campaigns campaigns = campaignsRepository.findById(idCampaign).orElse(null);

    if(campaigns == null){
      throw new Error("CampaignNotFound");
    }

    campaigns.setStatus(!campaigns.isStatus());

    campaignsRepository.saveAndFlush(campaigns);
  }
}
