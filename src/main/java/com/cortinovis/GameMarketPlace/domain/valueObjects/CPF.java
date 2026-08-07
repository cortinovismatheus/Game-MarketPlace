package com.cortinovis.GameMarketPlace.domain.valueObjects;


import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

public class CPF {

  private final String value;

  public CPF(String cpf) {

    String numbers = cpf.replaceAll("\\D", "");

    validate(numbers);

    this.value = numbers;
  }


  private void validate(String cpf) {

    if (cpf.length() != 11) {
      throw new IllegalArgumentException("CPF inválido");
    }

    if (cpf.chars().distinct().count() == 1) {
      throw new IllegalArgumentException("CPF inválido");
    }

    if (!isValid(cpf)) {
      throw new IllegalArgumentException("CPF inválido");
    }
  }


  public String getValue() {
    return value;
  }
}