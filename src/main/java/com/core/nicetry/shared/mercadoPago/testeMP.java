package com.core.nicetry.shared.mercadoPago;

import com.core.nicetry.shared.mercadoPago.services.verifyStatusPayment.VerifyPayment;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("mercadopago")
public class testeMP {


  @Value("${app.access_token_mp}")
  private String acessToken;


  @Autowired
  VerifyPayment verifyPayment;

  @GetMapping
  public ResponseEntity<Void> handle() throws MPException, MPApiException, IOException {
    verifyPayment.isPixPaid();
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
//    MercadoPagoConfig.setAccessToken(acessToken);
//
//    PaymentClient client = new PaymentClient();
//
//
//    PaymentCreateRequest paymentCreateRequest =
//      PaymentCreateRequest.builder()
//        .transactionAmount(new BigDecimal("100"))
//        .paymentMethodId("pix")
//        .payer(
//          PaymentPayerRequest.builder()
//            .email("rafael@gmail.com")
//            .build())
//        .build();
//
//    client.create(paymentCreateRequest);
//
//    Payment payment = client.create(paymentCreateRequest);
//    CreatePixResponseDTO createPixResponseDTO = new CreatePixResponseDTO();
//    createPixResponseDTO.setId(payment.getId());
//    createPixResponseDTO.setDate_created(payment.getDateCreated());
//    createPixResponseDTO.setDate_of_expiration(payment.getDateOfExpiration());
//    createPixResponseDTO.setStatus(payment.getStatus());
//    createPixResponseDTO.setStatus_detail(payment.getStatusDetail());
//    createPixResponseDTO.setTransaction_amount(payment.getTransactionAmount());
//    createPixResponseDTO.setContent(payment.getResponse().getContent());
//
//    System.err.println(createPixResponseDTO.getQr_code());
//
//    return new ResponseEntity<>(HttpStatus.OK);



