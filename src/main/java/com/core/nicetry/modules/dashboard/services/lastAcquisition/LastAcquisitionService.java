package com.core.nicetry.modules.dashboard.services.lastAcquisition;

import com.core.nicetry.modules.campaigns.repository.CampaignsRepository;
import com.core.nicetry.modules.order.entityes.Orders;
import com.core.nicetry.modules.order.repository.OrdersRepository;
import com.core.nicetry.modules.dashboard.services.lastAcquisition.dto.LastAcquisitionDTO;
import com.core.nicetry.modules.users.repository.UsersRepository;
import com.core.nicetry.utils.EmailMasker;
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

  @Autowired
  EmailMasker emailMasker;


  public List<LastAcquisitionDTO> execute() {
    List<LastAcquisitionDTO> response = new ArrayList<>();

    List<Orders> tickets = ticketsRepository.findTop5ByPaymentOrderByCheckoutDateDesc(true);

    for(Orders ticket: tickets){
      LastAcquisitionDTO acquisitionDTO = new LastAcquisitionDTO();

      acquisitionDTO.setNameCampaign(ticket.getCampaigns().getName());
      acquisitionDTO.setEmail(emailMasker.maskEmail(ticket.getUsers().getUsername()));
      acquisitionDTO.setName(ticket.getUsers().getName());
      acquisitionDTO.setValue(ticket.getCampaigns().getNumberValue().multiply(new BigDecimal(ticket.getNumbers())));
      response.add(acquisitionDTO);
    }

    return response;
  }
}
