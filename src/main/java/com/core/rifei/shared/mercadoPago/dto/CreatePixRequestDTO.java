package com.core.rifei.shared.mercadoPago.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreatePixRequestDTO {
  private String email;

  private String campaignName;

  private BigDecimal value;

  private UUID id;

  private String name;
}
