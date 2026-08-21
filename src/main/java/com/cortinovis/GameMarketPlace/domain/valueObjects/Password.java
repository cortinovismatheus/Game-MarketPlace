package com.cortinovis.GameMarketPlace.domain.valueObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Password {
  private String value;

  public Password(String value) {
    validations(value);
    this.value = value;
  }

  private void validations(String value) {
    if (value == null) {
      throw new IllegalArgumentException("Password cannot be null");
    }

    if (value.isBlank()) {
      throw new IllegalArgumentException("Password cannot be blank");
    }

    if (value.length() < 8) {
      throw new IllegalArgumentException("Password must have a 8 characters");
    }

    if (!value.matches(".*[A-Z].*")) {
      throw new IllegalArgumentException("Password must contain an uppercase letter");
    }

    if (!value.matches(".*\\d.*")) {
      throw new IllegalArgumentException("Password must contain a number");
    }
  }
}

