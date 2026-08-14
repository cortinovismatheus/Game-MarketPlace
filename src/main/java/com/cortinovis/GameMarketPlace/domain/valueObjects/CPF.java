package com.cortinovis.GameMarketPlace.domain.valueObjects;

public class CPF {

  private final String value;

  public CPF(String cpf) {

    if (cpf == null) {
      throw new IllegalArgumentException("Invalid CPF");
    }

    String numbers = cpf.replaceAll("\\D", "");

    validate(numbers);

    this.value = numbers;
  }

  private void validate(String cpf) {

    if (cpf.length() != 11) {
      throw new IllegalArgumentException("Invalid CPF");
    }

    if (cpf.chars().distinct().count() == 1) {
      throw new IllegalArgumentException("Invalid CPF");
    }

    if (!isValidCPF(cpf)) {
      throw new IllegalArgumentException("Invalid CPF");
    }
  }

  private boolean isValidCPF(String cpf) {

    int firstDigit = calculateDigit(cpf, 9);
    int secondDigit = calculateDigit(cpf, 10);

    return firstDigit == Character.getNumericValue(cpf.charAt(9))
            && secondDigit == Character.getNumericValue(cpf.charAt(10));
  }

  private int calculateDigit(String cpf, int position) {

    int sum = 0;
    int weight = position + 1;

    for (int i = 0; i < position; i++) {
      sum += Character.getNumericValue(cpf.charAt(i)) * weight;
      weight--;
    }

    int remainder = sum % 11;

    return remainder < 2 ? 0 : 11 - remainder;
  }

  public String getValue() {
    return value;
  }
}