package com.core.rifei.shared.SendEmail.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailServiceDTO {

    private String userMail;

    private String userName;

    private String link;

    private int templateType;

    private String campaignName;
}