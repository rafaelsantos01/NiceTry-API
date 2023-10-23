package com.core.rifei.modules.users.ENUM;

public enum ROLES {

  ROLE_ADMIN(0),
  ROLE_USER(1);

  private int role;

  ROLES(int role){
    this.role = role;
  }

  public int getKey(){
    return role;
  }
}
