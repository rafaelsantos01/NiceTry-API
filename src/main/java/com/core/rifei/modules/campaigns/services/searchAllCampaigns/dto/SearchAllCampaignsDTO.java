package com.core.rifei.modules.campaigns.services.searchAllCampaigns.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class SearchAllCampaignsDTO {

  private UUID id;

  private String name;

  private String description;

  private String label;

  private int maxNumbers;

  private BigDecimal productValue;

  private BigDecimal numberValue;

  private boolean status;

  private String type;

  private String urlImage;

  private int numberTicketsPurchased;
}
