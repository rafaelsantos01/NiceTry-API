package com.core.rifei.modules.dashboard.services.listAllUsers.dto;

import com.core.rifei.modules.users.ENUM.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ListAllUsersDTO {

  private UUID id;

  private String name;

  private String email;

  private String cpf;

  private UserRole permission;

  private boolean status;

  private String observation;

}
