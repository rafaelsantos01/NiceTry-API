package com.core.nicetry.modules.users.services.recoveryPassword.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RecoveryPasswordDTO {

  @NotNull
  @NotBlank
  @NotEmpty
  private String token;

  @NotNull
  @NotBlank
  @NotEmpty
  private String password;

  @NotNull
  @NotBlank
  @NotEmpty
  private String password_confirmation;
}
