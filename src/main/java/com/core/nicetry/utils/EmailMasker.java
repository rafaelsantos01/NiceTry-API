package com.core.nicetry.utils;

import org.springframework.stereotype.Service;

@Service
public class EmailMasker {

  public String maskEmail(String email) {
    if (email == null) {
      return "email@nicetry.com.br";
    }

    int index = email.indexOf("@");
    if (index == -1) {
      return email;
    }

    return email.substring(0, 5) + "******" + email.substring(index);
  }
}
