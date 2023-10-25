package com.core.rifei.modules.users.services.userData;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDataDTO {

  private UUID id;

  private String name;

  private String cpf;

  private String email;

  private String phoneNumber;

  private String observation;

  private boolean email_verify;
}
