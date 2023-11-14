package com.core.nicetry.shared.mercadoPago.ENUM;

public enum STATUSPAYMENTMP {

  PENDING("pending"),
  APPROVED("approved"),
  INPROCESS("inprocess"),
  INMEDIATION("inmediation"),
  REJECTED("rejected"),
  CANCELLED("cancelled"),
  REFUNDED("refunded"),
  CHANGEDBACK("chargedback");

  private String status;

  STATUSPAYMENTMP(String status){
    this.status = status;
  }

  public String getKey(){
    return status;
  }

}
