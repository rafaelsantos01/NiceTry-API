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

  private String urlImage;

  @NotNull @NotEmpty @Size(max = 20)
  private String type;

  @NotNull @NotEmpty @Size(max = 20)
  private String name;

  @NotNull @NotEmpty @Size(max = 34)
  private String description;

  @NotNull @NotEmpty @Size(max = 30)
  private String label;

  private int maxNumbers;

  private BigDecimal productValue;

  private BigDecimal numberValue;
}
