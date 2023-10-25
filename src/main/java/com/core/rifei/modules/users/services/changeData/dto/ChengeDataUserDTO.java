package com.core.rifei.modules.users.services.changeData.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChengeDataUserDTO {

  private String name;

  private String observation;

  private String numberPhone;

  private String cpf;
}
