package com.core.rifei.modules.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsersDTO {

  private UUID id;

  private String name;

  private String cpf;

  private String login;
}
