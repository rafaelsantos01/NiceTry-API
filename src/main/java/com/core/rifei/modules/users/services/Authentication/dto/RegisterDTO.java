package com.core.rifei.modules.users.services.Authentication.dto;

import com.core.rifei.modules.users.ENUM.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterDTO {

  @NotNull @NotBlank @NotEmpty
  private String name;

  private String cpf;

  @NotNull @NotBlank @NotEmpty
  private String login;

  private String phoneNumber;

  @NotNull @NotBlank @NotEmpty
  private String password;

  @NotNull @NotBlank @NotEmpty
  private String password_confirmation;

  private String tradeLink;

  private UserRole permission;

}
