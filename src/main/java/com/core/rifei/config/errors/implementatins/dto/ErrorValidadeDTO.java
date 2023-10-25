package com.core.rifei.config.errors.implementatins.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorValidadeDTO {

  private String field;

  private String message;

  public ErrorValidadeDTO(String field,String message){
    this.field = field;
    this.message = message;
  }
}
