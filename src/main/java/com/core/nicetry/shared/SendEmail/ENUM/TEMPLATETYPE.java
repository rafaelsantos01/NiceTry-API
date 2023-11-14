package com.core.nicetry.shared.SendEmail.ENUM;

public enum TEMPLATETYPE {

  NEW_USER(0),

  RESEND_CONFIRMATION_EMAIL(1),

  RECOVERY_PASSWORD(2),

  CHANGE_PASSWORD(3),

  PAYMENT(4),

  NUMBERLOOK(5);

  private int template;

  TEMPLATETYPE(int template){
    this.template = template;
  }

  public int getKey(){
    return template;
  }
}
