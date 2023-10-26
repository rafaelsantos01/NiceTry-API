package com.core.rifei.modules.winners.services.numberslook;

import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.campaigns.repository.CampaignsRepository;
import com.core.rifei.modules.order.entityes.Orders;
import com.core.rifei.modules.order.repository.OrdersRepository;
import com.core.rifei.modules.winners.entityes.Winner;
import com.core.rifei.modules.winners.repository.WinnerRepository;
import com.core.rifei.shared.SendEmail.SendEmailService;
import com.core.rifei.shared.SendEmail.ENUM.TEMPLATETYPE;
import com.core.rifei.shared.SendEmail.dto.SendEmailServiceDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NumbersLookService {

  @Autowired
  OrdersRepository ordersRepository;

  @Autowired
  CampaignsRepository campaignsRepository;

  @Autowired
  WinnerRepository winnerRepository;


  @Autowired
  SendEmailService sendEmailService;

  public void handle(UUID order_id){
    StringBuilder numbersLook= new StringBuilder();
    long currentTimeMillis = System.currentTimeMillis();
    Timestamp timestamp = new Timestamp(currentTimeMillis);
    SecureRandom secureRandom = new SecureRandom();

    Orders orders = ordersRepository.findById(order_id).orElse(null);
    if (orders == null) {
      throw new Error("OrderNotFound");
    }

    Campaigns campaigns = campaignsRepository.findById(orders.getCampaigns().getId()).orElse(null);
    if (campaigns == null) {
      throw new Error("CampaignNotFound");
    }

    List<Winner> existingWinners = winnerRepository.findByCampaigns(campaigns);

    if(existingWinners.size() < campaigns.getMaxNumbers()){
      List<Integer> availableNumbers = new ArrayList<>();
      List<Winner> winnersSave = new ArrayList<>();

      for (int i = 1; i <= campaigns.getMaxNumbers(); i++) {
        availableNumbers.add(i);
      }

      int numberOfTickets = orders.getNumbers();

      if (availableNumbers.size() < numberOfTickets) {
        throw new Error("NotEnoughAvailableNumbers");
      }

      for (int i = 0; i < numberOfTickets; i++) {
        int randomIndex;
        int uniqueNumber;

        do {
          randomIndex = secureRandom.nextInt(availableNumbers.size());
          uniqueNumber = availableNumbers.get(randomIndex);
        } while (isNumberUsed(existingWinners, uniqueNumber));

        numbersLook = new StringBuilder(uniqueNumber + "," + numbersLook);

        availableNumbers.remove(randomIndex);

        Winner winner = new Winner();
        winner.setPayment(true);
        winner.setIdUser(orders.getUsers());
        winner.setOrders(orders);
        winner.setNumber(uniqueNumber);
        winner.setCampaigns(orders.getCampaigns());
        winner.setWinner(false);
        winner.setDrawDate(timestamp);
        winnersSave.add(winner);
      }
      String numbersLookValid = StringUtils.removeEnd(numbersLook.toString(),",");

      SendEmailServiceDTO sendEmailServiceDTO = new SendEmailServiceDTO();
      sendEmailServiceDTO.setTemplateType(TEMPLATETYPE.NUMBERLOOK.getKey());
      sendEmailServiceDTO.setNumbersLook(numbersLookValid);
      sendEmailServiceDTO.setUserName(orders.getUsers().getName());
      sendEmailServiceDTO.setUserMail(orders.getUsers().getUsername());
      sendEmailServiceDTO.setCampaignName(campaigns.getName());
      sendEmailService.SendEmail(sendEmailServiceDTO);

      winnerRepository.saveAllAndFlush(winnersSave);
    }else{
      System.out.println("Há pedidos com mais números comprados do que disponíveis na campanha");
    }
  }

  private boolean isNumberUsed(List<Winner> winners, int numberToCheck) {
    for (Winner winner : winners) {
      if (winner.getNumber() == numberToCheck) {
        return true;
      }
    }
    return false;
  }
}
