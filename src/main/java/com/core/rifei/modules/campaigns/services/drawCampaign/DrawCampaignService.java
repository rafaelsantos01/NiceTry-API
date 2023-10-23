package com.core.rifei.modules.campaigns.services.drawCampaign;

import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.campaigns.repository.CampaignsRepository;
import com.core.rifei.modules.order.entityes.Orders;
import com.core.rifei.modules.order.repository.OrdersRepository;
import com.core.rifei.modules.winners.entityes.Winner;
import com.core.rifei.modules.winners.repository.WinnerRepository;
import com.core.rifei.shared.mercadoPago.ENUM.STATUSPAYMENTMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class DrawCampaignService {

  @Autowired
  CampaignsRepository campaignsRepository;


  @Autowired
  OrdersRepository ordersRepository;

  @Autowired
  WinnerRepository winnerRepository;

  public void execute(UUID idCampaign) {
    Random random = new Random();
    Campaigns campaigns = campaignsRepository.findById(idCampaign).orElse(null);
    if(campaigns == null){
      throw new Error("CampaignNotFound");
    }

    if(!campaigns.isStatus()){
      throw new Error("CampaignInactive");
    }

    List<Orders> ordersList = ordersRepository.findByCampaignsAndStatusPayment(campaigns, STATUSPAYMENTMP.APPROVED.getKey());

    int numbers = 0;
    for(Orders tickets : ordersList){
      numbers = numbers + tickets.getNumbers();
    }

    long currentTimeMillis = System.currentTimeMillis();
    Timestamp timestamp = new Timestamp(currentTimeMillis);

    List<Winner> winnersSave = new ArrayList<>();

    int contador = 0;
    for(Orders order : ordersList){
      for(int i = 1; i <= order.getNumbers() ;i++){
        contador = contador + 1;
        Winner winner = new Winner();
        winner.setPayment(true);
        winner.setIdUser(order.getUsers());
        winner.setOrders(order);
        winner.setNumber(contador);
        winner.setCampaigns(order.getCampaigns());
        winner.setDrawDate(timestamp);
        winnersSave.add(winner);
      }
    }

    int randomInt = random.nextInt(numbers)+1;

    for(Winner winner :winnersSave){
      if(winner.getNumber() == randomInt){
        winner.setWinner(true);
        break;
      }
    }

    winnerRepository.saveAllAndFlush(winnersSave);
    campaigns.setStatus(false);
    campaignsRepository.save(campaigns);
  }
}

