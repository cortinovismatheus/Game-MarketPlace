package com.cortinovis.GameMarketPlace.domain.valueObjects;

import lombok.Getter;

@Getter
public class ProductDescription {
  private String value;

  public ProductDescription(String value){
    validations(value);
    this.value = value;
  }

  private void validations(String value){
    if (value == null) {
      throw new IllegalArgumentException("Description cannot be null");
    }

    if (value.isBlank()) {
      throw new IllegalArgumentException("Description cannot be blank");
    }

    if (value.length() > 100) {
      throw new IllegalArgumentException(
              "Description cannot have more than 100 characters"
      );
    }
  }
}
