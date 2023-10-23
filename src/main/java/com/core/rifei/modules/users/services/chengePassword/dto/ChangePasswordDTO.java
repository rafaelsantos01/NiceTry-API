package com.core.rifei.modules.users.services.chengePassword.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangePasswordDTO {

  @NotNull @NotEmpty @NotBlank
  private String old_password;

  @NotNull @NotEmpty @NotBlank
  private String new_password;

  @NotNull @NotEmpty @NotBlank
  private String confirmation_new_password;
}
