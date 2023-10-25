package com.core.rifei.modules.campaigns.services.searchAllCampaigns;

import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.campaigns.repository.CampaignsRepository;
import com.core.rifei.modules.campaigns.services.searchAllCampaigns.dto.SearchAllCampaignsDTO;
import com.core.rifei.modules.order.entityes.Orders;
import com.core.rifei.modules.order.repository.OrdersRepository;
import com.core.rifei.shared.mercadoPago.ENUM.STATUSPAYMENTMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchAllCampaignsService {

  @Autowired
  CampaignsRepository campaignsRepository;


  @Autowired
  OrdersRepository ordersRepository;


  public List<SearchAllCampaignsDTO> execute(boolean active) {

    List<SearchAllCampaignsDTO> response = new ArrayList<>();
    List<Campaigns> campaigns = campaignsRepository.findByStatus(active);

    for(Campaigns campaign :campaigns ){
      SearchAllCampaignsDTO campaignDTO  = new SearchAllCampaignsDTO();

      campaignDTO.setId(campaign.getId());
      campaignDTO.setName(campaign.getName());
      campaignDTO.setDescription(campaign.getDescription());
      campaignDTO.setLabel(campaign.getLabel());
      campaignDTO.setMaxNumbers(campaign.getMaxNumbers());
      campaignDTO.setNumberValue(campaign.getNumberValue());
      campaignDTO.setProductValue(campaign.getProductValue());
      campaignDTO.setStatus(campaign.isStatus());
      campaignDTO.setUrlImage(campaign.getUrlImage());
      campaignDTO.setType(campaign.getType());

      if(active){
        List<Orders> orders =  ordersRepository.findAllByCampaigns(campaign);
        int contador = 0;
        for(Orders order : orders){
          if(order.getStatusPayment().equals(STATUSPAYMENTMP.PENDING.getKey()) ||
            order.getStatusPayment().equals(STATUSPAYMENTMP.APPROVED.getKey()) ||
            order.getStatusPayment().equals(STATUSPAYMENTMP.INPROCESS.getKey())){
            contador = contador + order.getNumbers();
          }
        }
        campaignDTO.setNumberTicketsPurchased(contador);
      }

      response.add(campaignDTO);
    }

    return response;
  }
}
