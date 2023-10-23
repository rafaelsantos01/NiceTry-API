package com.core.rifei.modules.campaigns.services.createNewCampaign.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateCampaignDTO {

  private String urlImage;

  private String name;

  private String description;

  private String label;

  private int maxNumbers;

  private BigDecimal productValue;

  private BigDecimal numberValue;
}
