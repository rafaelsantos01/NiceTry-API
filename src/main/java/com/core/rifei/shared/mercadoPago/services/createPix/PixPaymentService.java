package com.core.rifei.shared.mercadoPago.services.createPix;

import com.core.rifei.shared.mercadoPago.dto.CreatePixRequestDTO;
import com.core.rifei.shared.mercadoPago.dto.CreatePixResponseDTO;
import com.core.rifei.shared.mercadoPago.dto.GetQRCodeAndTicketURLDTO;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.*;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class PixPaymentService {

  @Value("${app.access_token_mp}")
  private String acessToken;

  public CreatePixResponseDTO CreatePix(CreatePixRequestDTO data){
    try{
      MercadoPagoConfig.setAccessToken(acessToken);

      PaymentClient client = new PaymentClient();

      List<PaymentItemRequest> items = new ArrayList<>();

      String[] nameParts = data.getName().split(" ", 2);
      String firstName = nameParts[0];
      String lastName = nameParts.length > 1 ? nameParts[1] : "";

      PaymentItemRequest item =
        PaymentItemRequest.builder()
          .id(data.getId().toString())
          .title(data.getItem().getTitle())
          .description(data.getItem().getDescription())
          .pictureUrl(data.getItem().getImgUrl())
          .categoryId(data.getItem().getCategory())
          .quantity(data.getItem().getQuantity())
          .unitPrice(data.getItem().getUnitPrice())
          .build();

      items.add(item);

      PaymentCreateRequest paymentCreateRequest =
        PaymentCreateRequest.builder()
          .additionalInfo(
                  PaymentAdditionalInfoRequest.builder()
                    .items(items)
                    .payer(
                      PaymentAdditionalInfoPayerRequest.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .build()
                ).build())
          .transactionAmount(data.getValue())
          .paymentMethodId("pix")
          .description("Campanha: "+ data.getItem().getTitle() + " " + data.getItem().getDescription())
          .payer(
            PaymentPayerRequest.builder()
              .firstName(firstName)
              .lastName(lastName)
              .email(data.getEmail())
              .build())
          .build();

      client.create(paymentCreateRequest);

      Payment payment = client.create(paymentCreateRequest);

      CreatePixResponseDTO createPixResponseDTO = new CreatePixResponseDTO();
      createPixResponseDTO.setId(payment.getId());
      createPixResponseDTO.setDate_created(payment.getDateCreated());
      createPixResponseDTO.setDate_of_expiration(payment.getDateOfExpiration());
      createPixResponseDTO.setStatus(payment.getStatus());
      createPixResponseDTO.setStatus_detail(payment.getStatusDetail());
      createPixResponseDTO.setTransaction_amount(payment.getTransactionAmount());
      createPixResponseDTO.setContent(payment.getResponse().getContent());

      GetQRCodeAndTicketURLDTO qrCode = getQRCode(payment.getResponse().getContent());
      createPixResponseDTO.setQr_code(qrCode.getQr_code());
      createPixResponseDTO.setTicket_url(qrCode.getTicket_url());

      return createPixResponseDTO;
    }catch (Exception e){
      throw new Error("ErrorCreatedPix");
    }
  }


  public GetQRCodeAndTicketURLDTO getQRCode(String qrcode){
    Pattern qrcodeString = Pattern.compile("\"qr_code\":\\s*\"([^\"]+)\"");
    Pattern ticketUrl = Pattern.compile("\"ticket_url\":\\s*\"([^\"]+)\"");

    Matcher matcher1 = qrcodeString.matcher(qrcode);
    Matcher matcher2 = ticketUrl.matcher(qrcode);

    GetQRCodeAndTicketURLDTO ticketURLDTO = new GetQRCodeAndTicketURLDTO();

    if (matcher1.find()) {
      ticketURLDTO.setQr_code(matcher1.group(1));
    }

    if (matcher2.find()) {
      ticketURLDTO.setTicket_url(matcher2.group(1));
    }

    return ticketURLDTO;
  }


}
