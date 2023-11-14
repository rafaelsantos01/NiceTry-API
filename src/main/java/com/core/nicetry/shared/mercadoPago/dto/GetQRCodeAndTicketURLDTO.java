package com.core.nicetry.shared.mercadoPago.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetQRCodeAndTicketURLDTO {

  private String ticket_url;

  private String qr_code;
}
