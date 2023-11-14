package com.core.nicetry.utils;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidation {

  public boolean execute(String senha) {
    // Usar expressões regulares para validar a senha
    String regexMaiuscula = ".*[A-Z].*";
    String regexMinuscula = ".*[a-z].*";
    String regexCaractereEspecial = ".*[^A-Za-z0-9].*";
    String regexDigitos = ".*\\d.*";

    // Verificar se a senha atende a todos os critérios
    boolean temMaiuscula = senha.matches(regexMaiuscula);
    boolean temMinuscula = senha.matches(regexMinuscula);
    boolean temCaractereEspecial = senha.matches(regexCaractereEspecial);
    boolean temDigitos = senha.matches(regexDigitos);

    // Verificar se atende a todos os critérios
    return temMaiuscula && temMinuscula && temCaractereEspecial && temDigitos && senha.length() >= 8;
  }
}
