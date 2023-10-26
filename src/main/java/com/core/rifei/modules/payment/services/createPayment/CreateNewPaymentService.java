package com.core.rifei.modules.payment.services.createPayment;

import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.campaigns.repository.CampaignsRepository;
import com.core.rifei.modules.payment.services.createPayment.dto.CreatePaymentRequestDTO;
import com.core.rifei.modules.payment.services.createPayment.dto.CreatePaymentResponseDTO;
import com.core.rifei.modules.order.entityes.Orders;
import com.core.rifei.modules.order.repository.OrdersRepository;
import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.users.repository.UsersRepository;
import com.core.rifei.security.context.SetUserService;
import com.core.rifei.shared.SendEmail.SendEmailService;
import com.core.rifei.shared.SendEmail.ENUM.TEMPLATETYPE;
import com.core.rifei.shared.SendEmail.dto.SendEmailServiceDTO;
import com.core.rifei.shared.mercadoPago.ENUM.STATUSPAYMENTMP;
import com.core.rifei.shared.mercadoPago.dto.ItemCampaignDTO;
import com.core.rifei.shared.mercadoPago.services.createPix.PixPaymentService;
import com.core.rifei.shared.mercadoPago.dto.CreatePixRequestDTO;
import com.core.rifei.shared.mercadoPago.dto.CreatePixResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreateNewPaymentService {

  @Autowired
  CampaignsRepository campaignsRepository;

  @Autowired
  SetUserService setUserService;


  @Autowired
  OrdersRepository ordersRepository;


  @Autowired
  UsersRepository usersRepository;


  @Autowired
  PixPaymentService pixPaymentService;


  @Autowired
  SendEmailService sendEmailService;

  public CreatePaymentResponseDTO execute(CreatePaymentRequestDTO data) {

    Users user = setUserService.execute();

    Users users = usersRepository.findById(user.getId()).orElse(null);
    if(users == null){
      throw new Error("UserNotFound");
    }

    Campaigns campaigns = campaignsRepository.findById(data.getCampaingId()).orElse(null);
    if(campaigns == null){
      throw new Error("CampaignNotFound");
    }

    if(!campaigns.isStatus()){
      throw new Error("CampaignInactive");
    }

    List<Orders> ticketsCampaigns = ordersRepository.findAllByCampaigns(campaigns);

    int maxCheckoutCampaign = 0;
    int canceladas = 0;
    for(Orders tickets: ticketsCampaigns){
      maxCheckoutCampaign += tickets.getNumbers();
      if(tickets.getStatusPayment().equals(STATUSPAYMENTMP.CANCELLED.getKey())){
        canceladas += tickets.getNumbers();
      }
    }
    maxCheckoutCampaign -= canceladas;

    if (maxCheckoutCampaign >=  campaigns.getMaxNumbers() ||
      data.getQuantity() > campaigns.getMaxNumbers() ||
      data.getQuantity()+maxCheckoutCampaign > campaigns.getMaxNumbers()) {
      throw new Error("MaxNumbersSold");
    }

    Orders orders = new Orders();
    orders.setCampaigns(campaigns);
    orders.setUsers(user);
    orders.setPayment(false);
    orders.setNumbers(data.getQuantity());

    CreatePaymentResponseDTO response = new CreatePaymentResponseDTO();

    ItemCampaignDTO itemCampaignDTO = new ItemCampaignDTO();
    itemCampaignDTO.setCategory("Campanha");
    itemCampaignDTO.setTitle(campaigns.getName());
    itemCampaignDTO.setQuantity(data.getQuantity());
    itemCampaignDTO.setUnitPrice(campaigns.getNumberValue());
    itemCampaignDTO.setImgUrl(campaigns.getUrlImage());
    itemCampaignDTO.setDescription(campaigns.getDescription());

    CreatePixRequestDTO createPixRequestDTO = new CreatePixRequestDTO();
    createPixRequestDTO.setEmail(users.getLogin());
    createPixRequestDTO.setName(users.getName());
    createPixRequestDTO.setId(users.getId());
    createPixRequestDTO.setValue(campaigns.getNumberValue().multiply(new BigDecimal(data.getQuantity())));
    createPixRequestDTO.setItem(itemCampaignDTO);

    CreatePixResponseDTO createPixResponseDTO = pixPaymentService.CreatePix(createPixRequestDTO);

    response.setUrl(createPixResponseDTO.getQr_code());
    response.setValue(createPixRequestDTO.getValue().toString());

    orders.setIdTransaction(createPixResponseDTO.getId());
    orders.setQRCode(createPixResponseDTO.getQr_code());
    orders.setStatusPayment(createPixResponseDTO.getStatus());
    orders.setTicketUrl(createPixResponseDTO.getTicket_url());
    orders.setTransactionAmount(createPixResponseDTO.getTransaction_amount());

    SendEmailServiceDTO sendEmailServiceDTO = new SendEmailServiceDTO();
    sendEmailServiceDTO.setTemplateType(TEMPLATETYPE.PAYMENT.getKey());
    sendEmailServiceDTO.setUserName(users.getName());
    sendEmailServiceDTO.setUserMail(users.getUsername());
    sendEmailServiceDTO.setLink(createPixResponseDTO.getTicket_url());
    sendEmailServiceDTO.setCampaignName(campaigns.getName());

    sendEmailService.SendEmail(sendEmailServiceDTO);

    ordersRepository.saveAndFlush(orders);

    return response;
  }
}
