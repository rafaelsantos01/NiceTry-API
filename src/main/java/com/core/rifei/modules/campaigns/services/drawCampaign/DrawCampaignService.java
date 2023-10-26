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

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.*;

@Service
public class DrawCampaignService {

  @Autowired
  CampaignsRepository campaignsRepository;


  @Autowired
  OrdersRepository ordersRepository;

  @Autowired
  WinnerRepository winnerRepository;

  public void execute(UUID idCampaign) {
//    Random random = new Random();
//    SecureRandom secureRandom = new SecureRandom();
//
//    Campaigns campaigns = campaignsRepository.findById(idCampaign).orElse(null);
//    if(campaigns == null){
//      throw new Error("CampaignNotFound");
//    }
//
//    if(!campaigns.isStatus()){
//      throw new Error("CampaignInactive");
//    }
//
//    List<Orders> ordersList = ordersRepository.findByCampaignsAndStatusPayment(campaigns, STATUSPAYMENTMP.APPROVED.getKey());
//
//    int numbers = 0;
//    for(Orders tickets : ordersList){
//      numbers = numbers + tickets.getNumbers();
//    }
//
//    long currentTimeMillis = System.currentTimeMillis();
//    Timestamp timestamp = new Timestamp(currentTimeMillis);
//
//    List<Winner> winnersSave = new ArrayList<>();
//
//    int contador = 0;
//    for(Orders order : ordersList){
//      for(int i = 1; i <= order.getNumbers() ;i++){
//        contador = contador + 1;
//        Winner winner = new Winner();
//        winner.setPayment(true);
//        winner.setIdUser(order.getUsers());
//        winner.setOrders(order);
//        winner.setNumber(contador);
//        winner.setCampaigns(order.getCampaigns());
//        winner.setDrawDate(timestamp);
//        winnersSave.add(winner);
//      }
//    }
//
//    int randomInt = random.nextInt(numbers)+1;
//
//    for(Winner winner :winnersSave){
//      if(winner.getNumber() == randomInt){
//        winner.setWinner(true);
//        break;
//      }
//    }
//
//    winnerRepository.saveAllAndFlush(winnersSave);
//    campaigns.setStatus(false);
//    campaignsRepository.save(campaigns);

//    SecureRandom secureRandom = new SecureRandom();
//    Campaigns campaign = campaignsRepository.findById(idCampaign).orElse(null);
//
//    if (campaign == null) {
//      throw new Error("CampaignNotFound");
//    }
//
//    if (!campaign.isStatus()) {
//      throw new Error("CampaignInactive");
//    }
//
//    int maxNumbers = campaign.getMaxNumbers();
//
//    List<Orders> ordersList = ordersRepository.findByCampaignsAndStatusPayment(campaign, STATUSPAYMENTMP.APPROVED.getKey());
//
//    List<Winner> winnersSave = new ArrayList<>();
//    List<Integer> availableNumbers = new ArrayList<>();
//
//    for (int i = 1; i <= maxNumbers; i++) {
//      availableNumbers.add(i);
//    }
//
//    for (Orders order : ordersList) {
//      long currentTimeMillis = System.currentTimeMillis();
//      Timestamp timestamp = new Timestamp(currentTimeMillis);
//
//      int numberOfTickets = order.getNumbers();
//
//      if (availableNumbers.size() < numberOfTickets) {
//        throw new Error("NotEnoughAvailableNumbers");
//      }
//
//      List<Integer> selectedNumbers = new ArrayList<>();
//
//      for (int i = 0; i < numberOfTickets; i++) {
//        int randomIndex = secureRandom.nextInt(availableNumbers.size());
//        int uniqueNumber = availableNumbers.remove(randomIndex);
//        selectedNumbers.add(uniqueNumber);
//
//        Winner winner = new Winner();
//        winner.setPayment(true);
//        winner.setIdUser(order.getUsers());
//        winner.setOrders(order);
//        winner.setNumber(uniqueNumber);
//        winner.setCampaigns(order.getCampaigns());
//        winner.setDrawDate(timestamp);
//        winner.setWinner(false);
//        winnersSave.add(winner);
//      }
//    }
//
//    int numberOfWinners = winnersSave.size();
//
//    if (numberOfWinners > 0) {
//      int randomIndex = secureRandom.nextInt(numberOfWinners);
//      winnersSave.get(randomIndex).setWinner(true);
//    }
//
//    winnerRepository.saveAllAndFlush(winnersSave);
//
//    campaign.setStatus(false);
//    campaignsRepository.save(campaign);

    SecureRandom secureRandom = new SecureRandom();
    long currentTimeMillis = System.currentTimeMillis();
    Timestamp timestamp = new Timestamp(currentTimeMillis);

    Campaigns campaign = campaignsRepository.findById(idCampaign).orElse(null);

    if (campaign == null) {
      throw new Error("CampaignNotFound");
    }

    if (!campaign.isStatus()) {
      throw new Error("CampaignInactive");
    }

    List<Winner> winners = winnerRepository.findByPaymentTrueAndCampaigns(campaign);

    List<Integer> availableNumbers = new ArrayList<>();
    for (Winner winner :winners) {
      availableNumbers.add(winner.getNumber());
    }

    int randomIndex = secureRandom.nextInt(availableNumbers.size());
    int selectedNumber = availableNumbers.get(randomIndex);


    for (Winner winner :winners) {
      if(winner.getNumber() == selectedNumber){
        winner.setWinner(true);
      }
      winner.setDrawDate(timestamp);
    }

    campaign.setStatus(false);
    campaignsRepository.save(campaign);
    winnerRepository.saveAllAndFlush(winners);
  }
}

