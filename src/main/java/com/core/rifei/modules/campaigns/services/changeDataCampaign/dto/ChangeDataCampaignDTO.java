package com.core.rifei.modules.campaigns.services.changeDataCampaign.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ChangeDataCampaignDTO {
  private String description;
  private String name;
  private String label;
  private String type;
  private int maxNumbers;
  private BigDecimal productValue;
  private BigDecimal numberValue;
  private String urlImage;
}
