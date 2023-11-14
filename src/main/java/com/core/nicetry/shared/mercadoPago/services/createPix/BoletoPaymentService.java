package com.core.nicetry.shared.mercadoPago.services.createPix;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class BoletoPaymentService {

  @Value("${app.access_token_mp}")
  private String acessToken;


  public void createBoleto()  {

    OffsetDateTime currentDate = OffsetDateTime.now(ZoneOffset.UTC);
    OffsetDateTime expirationDate = currentDate.plusDays(1);

    try{
      MercadoPagoConfig.setAccessToken(acessToken);
      PaymentClient client = new PaymentClient();
      PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
        .transactionAmount(new BigDecimal("100"))
        .description("Título do produto")
        .paymentMethodId("bolbradesco")
        .dateOfExpiration(expirationDate)
        .payer(
          PaymentPayerRequest.builder()
            .email("test@test.com")
            .firstName("Test")
            .lastName("User")
            .identification(
              IdentificationRequest.builder().type("CPF").number("19119119100").build())
            .build())
        .build();

      client.create(paymentCreateRequest);
    }catch (Exception e){
      throw new Error("ErrorCreatedPix");
    }
  }
}
