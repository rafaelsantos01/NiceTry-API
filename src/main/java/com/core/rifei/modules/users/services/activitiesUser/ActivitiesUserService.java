package com.core.rifei.modules.users.services.activitiesUser;

import com.core.rifei.modules.order.entityes.Orders;
import com.core.rifei.modules.order.repository.OrdersRepository;
import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.services.activitiesUser.dto.ActivitiesUserResponseDTO;
import com.core.rifei.modules.winners.entityes.Winner;
import com.core.rifei.modules.winners.repository.WinnerRepository;
import com.core.rifei.security.context.GetUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivitiesUserService {

  @Autowired
  OrdersRepository ordersRepository;

  @Autowired
  GetUserService setUserService;

  @Autowired
  WinnerRepository winnerRepository;

  public List<ActivitiesUserResponseDTO> execute() {
    Users user = setUserService.execute();

    List<ActivitiesUserResponseDTO> responseDTOS = new ArrayList<>();

    List<Orders> listOrders = ordersRepository.findByUsers(user);

    for(Orders order :listOrders){
      StringBuilder numbersLook= new StringBuilder();

      boolean userWinner = false;
      List<Winner> byCampaigns = winnerRepository.findByIdUserAndOrdersAndCampaigns(order.getUsers(),order,order.getCampaigns());
      for(Winner winner: byCampaigns){
        numbersLook = new StringBuilder(winner.getNumber() + " ," + numbersLook);
        if(winner.isWinner()){
          userWinner = true;
        }
      }

      String numbersLookValid = StringUtils.removeEnd(numbersLook.toString(),",");
      ActivitiesUserResponseDTO  activitiesUserResponseDTO = new ActivitiesUserResponseDTO();
      activitiesUserResponseDTO.setId(order.getId());
      activitiesUserResponseDTO.setPayment(order.isPayment());
      activitiesUserResponseDTO.setName(order.getCampaigns().getName());
      activitiesUserResponseDTO.setDescription(order.getCampaigns().getDescription());
      activitiesUserResponseDTO.setUrlImage(order.getCampaigns().getUrlImage());
      activitiesUserResponseDTO.setType(order.getCampaigns().getType());
      activitiesUserResponseDTO.setNumbersLook(numbersLookValid);
      activitiesUserResponseDTO.setQrCode(order.getQRCode());
      activitiesUserResponseDTO.setDrawCampaign(order.getCampaigns().isStatus());
      activitiesUserResponseDTO.setWinner(userWinner);

      responseDTOS.add(activitiesUserResponseDTO);
    }

    return responseDTOS;
  }
}
