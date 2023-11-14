package com.core.nicetry.utils;

import org.springframework.stereotype.Service;

@Service
public class MaskCPF {

  public String execute(String cpf) {
    if (cpf.length() != 11) {
     return  null;
    }
    // Mascara o CPF
    String cpfMascarado = cpf.substring(0, 4) + "*****" + cpf.substring(9, 11);

    return cpfMascarado;
  }

}
