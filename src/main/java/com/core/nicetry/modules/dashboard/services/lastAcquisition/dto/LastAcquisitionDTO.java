package com.core.nicetry.modules.dashboard.services.lastAcquisition.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LastAcquisitionDTO {

  private String name;

  private String nameCampaign;

  private String email;

  private BigDecimal value;
}
