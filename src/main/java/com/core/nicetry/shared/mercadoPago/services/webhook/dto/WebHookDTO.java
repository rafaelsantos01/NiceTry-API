package com.core.nicetry.shared.mercadoPago.services.webhook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebHookDTO {

  private String action;

  private String api_version;

  private  String application_id;

  private String date_created;

  private String id;

  private String live_mode;

  private String type;

  private String user_id;

  private  DataWebhookDTO data;
}
