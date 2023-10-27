package com.core.rifei.modules.campaigns.services.createNewCampaign;

import com.core.rifei.modules.campaigns.dto.CampaignDTO;
import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.campaigns.repository.CampaignsRepository;
import com.core.rifei.modules.campaigns.services.createNewCampaign.dto.CreateCampaignDTO;
import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.security.context.SetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreateNewCampaignService {

  @Autowired
  CampaignsRepository campaignsRepository;


  public CampaignDTO execute(CreateCampaignDTO data) {

    if(data.getMaxNumbers() <= 5){
      throw new Error("CampaignMustHaveMoreThan5Numbers");
    }

    if(data.getType().length() > 20){
      throw new Error("maximumNumberType");
    }

    if(data.getName().length() > 20){
      throw new Error("maximumNumberName");
    }

    if(data.getDescription().length() > 30){
      throw new Error("maximumNumberDescription");
    }

    if(data.getLabel().length() > 30){
      throw new Error("maximumNumberLabel");
    }

    CampaignDTO campaignDTO = new CampaignDTO();

    Campaigns campaigns = new Campaigns();


    campaigns.setName(data.getName());
    campaigns.setDescription(data.getDescription());
    campaigns.setLabel(data.getLabel());
    campaigns.setNumberValue(data.getNumberValue());
    campaigns.setProductValue(data.getProductValue());
    campaigns.setMaxNumbers(data.getMaxNumbers());
    campaigns.setUrlImage(data.getUrlImage());
    campaigns.setStatus(true);
    campaigns.setType(data.getType());

    Campaigns campaign = campaignsRepository.saveAndFlush(campaigns);

    campaignDTO.setId(campaign.getId());
    campaignDTO.setName(campaign.getName());
    campaignDTO.setDescription(campaign.getDescription());
    campaignDTO.setLabel(campaign.getLabel());
    campaignDTO.setMaxNumbers(campaign.getMaxNumbers());
    campaignDTO.setNumberValue(campaign.getNumberValue());
    campaignDTO.setProductValue(campaign.getProductValue());
    campaignDTO.setStatus(campaign.isStatus());
    campaignDTO.setType(campaign.getType());

    return campaignDTO;
  }
}
