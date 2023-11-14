package com.core.nicetry.shared.mercadoPago.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemCampaignDTO {

  private String title;

  private String category;

  private String description;

  private String imgUrl;

  private int quantity;

  private BigDecimal unitPrice;
}
