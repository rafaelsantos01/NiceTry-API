package com.core.rifei.shared.mercadoPago.services.createPix;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class CardPaymentService {

  @Value("${app.access_token_mp}")
  private String acessToken;


  public void createCard()  {

    OffsetDateTime currentDate = OffsetDateTime.now(ZoneOffset.UTC);
    OffsetDateTime expirationDate = currentDate.plusDays(1);

    try{
//      PaymentClient client = new PaymentClient();
//
//      PaymentCreateRequest paymentCreateRequest =
//        PaymentCreateRequest.builder()
//          .transactionAmount()
//          .token(request.getToken())
//          .description(request.getDescription())
//          .installments(request.getInstallments())
//          .paymentMethodId(request.getPaymentMethodId())
//          .payer(
//            PaymentPayerRequest.builder()
//              .email(request.getPayer().getEmail())
//              .firstName(request.getPayer().getFirstName())
//              .identification(
//                IdentificationRequest.builder()
//                  .type(request.getPayer().getIdentification().getType())
//                  .number(request.getPayer().getIdentification().getNumber())
//                  .build())
//              .build())
//          .build();
//
//      client.create(paymentCreateRequest);
    }catch (Exception e){
      throw new Error("ErrorCreatedPix");
    }
  }
}
