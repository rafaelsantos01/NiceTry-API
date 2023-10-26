package com.core.rifei.modules.campaigns.services.listResultDrawCampaign;

import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.campaigns.repository.CampaignsRepository;
import com.core.rifei.modules.campaigns.services.listResultDrawCampaign.dtos.ListResultWiner;
import com.core.rifei.modules.winners.entityes.Winner;
import com.core.rifei.modules.winners.repository.WinnerRepository;
import com.core.rifei.utils.EmailMasker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ListResultCampaignService {

  @Autowired
  WinnerRepository winnerRepository;


  @Autowired
  CampaignsRepository campaignsRepository;


  @Autowired
  EmailMasker emailMasker;


  public List<ListResultWiner> execute(UUID idCampaign) {

    List<ListResultWiner> response = new ArrayList<>();

    Campaigns campaigns = campaignsRepository.findById(idCampaign).orElse(null);
    if(campaigns == null){
      throw new Error("CampaignNotFound");
    }

    if(campaigns.isStatus()){
      throw new Error("CampaignActive");
    }

    List<Winner>  winnerList =  winnerRepository.findByPaymentTrueAndCampaigns(campaigns);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    for(Winner winner:winnerList){
      ListResultWiner listResultWiner = new ListResultWiner();

      listResultWiner.setEmail(emailMasker.maskEmail(winner.getIdUser().getLogin()));
      listResultWiner.setUserName(winner.getIdUser().getName());
      listResultWiner.setCampaignName(winner.getCampaigns().getName());
      listResultWiner.setNumber(winner.getNumber());
      listResultWiner.setDataCompra(sdf.format(winner.getOrders().getCheckoutDate()));
      listResultWiner.setDateDraw(sdf.format(winner.getDrawDate().getTime()));
      listResultWiner.setWinner(winner.isWinner());

      response.add(listResultWiner);
    }

    return response;
  }
}
