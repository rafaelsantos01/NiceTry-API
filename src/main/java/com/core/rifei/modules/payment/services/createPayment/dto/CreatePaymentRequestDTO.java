package com.core.rifei.modules.payment.services.createPayment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePaymentRequestDTO {

  private UUID campaingId;

  private int quantity;

}
