package com.core.rifei.modules.campaigns.services.createNewCampaign.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateCampaignDTO {

  @NotNull @NotEmpty
  private String urlImage;

  @NotNull @NotEmpty
  private String type;

  @NotNull @NotEmpty
  private String name;

  @NotNull @NotEmpty
  private String description;

  @NotNull @NotEmpty
  private String label;

  private int maxNumbers;

  private BigDecimal productValue;

  private BigDecimal numberValue;
}
