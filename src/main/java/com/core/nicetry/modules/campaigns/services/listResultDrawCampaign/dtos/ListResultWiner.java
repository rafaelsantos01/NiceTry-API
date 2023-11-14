package com.core.nicetry.modules.campaigns.services.listResultDrawCampaign.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListResultWiner {

  private String userName;

  private String email;

  private String campaignName;

  private int number;

  private String dataCompra;

  private String dateDraw;

  private boolean winner;
}
