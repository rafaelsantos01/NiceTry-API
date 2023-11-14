package com.core.nicetry.modules.users.services.Authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

  private String access_token;

  private String refresh_token;

  private List<String> permission;
}
