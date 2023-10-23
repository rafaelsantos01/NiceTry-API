package com.core.rifei.modules.dashboard.services.lastAcquisition;

import com.core.rifei.modules.campaigns.repository.CampaignsRepository;
import com.core.rifei.modules.order.entityes.Orders;
import com.core.rifei.modules.order.repository.OrdersRepository;
import com.core.rifei.modules.dashboard.services.lastAcquisition.dto.LastAcquisitionDTO;
import com.core.rifei.modules.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class LastAcquisitionService {


  @Autowired
  OrdersRepository ticketsRepository;

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  CampaignsRepository campaignsRepository;


  public List<LastAcquisitionDTO> execute() {
    List<LastAcquisitionDTO> response = new ArrayList<>();

    List<Orders> tickets = ticketsRepository.findTop5ByPaymentOrderByCheckoutDateDesc(true);

    for(Orders ticket: tickets){
      LastAcquisitionDTO acquisitionDTO = new LastAcquisitionDTO();

      acquisitionDTO.setNameCampaign(ticket.getCampaigns().getName());
      acquisitionDTO.setEmail(ticket.getUsers().getUsername());
      acquisitionDTO.setName(ticket.getUsers().getName());
      acquisitionDTO.setValue(ticket.getCampaigns().getNumberValue().multiply(new BigDecimal(ticket.getNumbers())));
      response.add(acquisitionDTO);
    }

    return response;
  }
}
