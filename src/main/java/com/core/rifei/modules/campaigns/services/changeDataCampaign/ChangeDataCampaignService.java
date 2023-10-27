package com.core.rifei.modules.campaigns.services.changeDataCampaign;

import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.campaigns.repository.CampaignsRepository;
import com.core.rifei.modules.campaigns.services.changeDataCampaign.dto.ChangeDataCampaignDTO;
import com.core.rifei.modules.order.entityes.Orders;
import com.core.rifei.modules.order.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChangeDataCampaignService {

  @Autowired
  CampaignsRepository campaignsRepository;

  @Autowired
  OrdersRepository ordersRepository;

  public void execute(UUID idCampaign, ChangeDataCampaignDTO data) {

    Campaigns campaigns = campaignsRepository.findById(idCampaign).orElse(null);
    if(campaigns == null){
      throw new Error("CampaignNotFound");
    }

    List<Orders> orders = ordersRepository.findAllByCampaigns(campaigns);

    campaigns.setId(idCampaign);
    campaigns.setType(data.getType());
    campaigns.setName(data.getName());
    campaigns.setDescription(data.getDescription());
    campaigns.setLabel(data.getLabel());
    if(data.getMaxNumbers() > 5){
      campaigns.setMaxNumbers(data.getMaxNumbers());
    }
    campaigns.setProductValue(data.getProductValue());
    campaigns.setUrlImage(data.getUrlImage());

    if(orders.isEmpty()){
      campaigns.setNumberValue(data.getNumberValue());
    }
    campaignsRepository.saveAndFlush(campaigns);
  }
}
