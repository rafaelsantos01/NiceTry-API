package com.core.nicetry.shared.mercadoPago.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class CreatePixResponseDTO {

  private Long id;

  private OffsetDateTime date_created;

  private OffsetDateTime date_of_expiration;

  private String status;

  private String status_detail;

  private BigDecimal transaction_amount;

  private String qr_code;

  private String content;

  private String ticket_url;
}
