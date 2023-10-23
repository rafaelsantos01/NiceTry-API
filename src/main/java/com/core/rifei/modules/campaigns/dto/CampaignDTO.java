package com.core.rifei.modules.campaigns.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CampaignDTO {

  private UUID id;

  private String name;

  private String description;

  private String label;

  private int maxNumbers;

  private BigDecimal productValue;

  private BigDecimal numberValue;

  private boolean status;

  private String urlImage;

}
