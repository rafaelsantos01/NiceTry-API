package com.core.nicetry.modules.campaigns.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CampaignDTO {

  private UUID id;

  private String type;

  private String name;

  private String description;

  private String label;

  private int maxNumbers;

  private BigDecimal productValue;

  private BigDecimal numberValue;

  private boolean status;

  private String urlImage;

}
