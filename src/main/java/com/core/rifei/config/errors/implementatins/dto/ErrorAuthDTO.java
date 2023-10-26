package com.core.rifei.config.errors.implementatins.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorAuthDTO {

  private String code;
  private String message;

  public ErrorAuthDTO(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
