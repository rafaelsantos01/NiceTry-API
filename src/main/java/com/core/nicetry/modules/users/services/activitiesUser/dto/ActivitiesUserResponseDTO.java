package com.core.nicetry.modules.users.services.activitiesUser.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ActivitiesUserResponseDTO {
  private UUID id;
  private String name;
  private String description;
  private String type;
  private String urlImage;
  private String numbersLook;
  private boolean payment;
  private boolean drawCampaign;
  private boolean winner;
  private String qrCode;
}
