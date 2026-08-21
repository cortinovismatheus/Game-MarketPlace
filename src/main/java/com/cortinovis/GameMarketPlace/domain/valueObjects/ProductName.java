package com.cortinovis.GameMarketPlace.domain.valueObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductName {
  private String value;

  public ProductName(String value){
    validations(value);
    this.value = value;
  }

  private void validations(String value){
    if(value == null){
      throw new IllegalArgumentException("Product name cannot be null");
    }

    if(value.isBlank()){
      throw new IllegalArgumentException("Product name cannot be is blank");
    }

    if(value.length() < 4){
      throw new IllegalArgumentException("Product name must have a 4 characters");
    }

    if (value.length() > 100) {
      throw new IllegalArgumentException("Product name must have at most 100 characters");
    }

    if (value.matches("\\d+")) {
      throw new IllegalArgumentException("Product name cannot contain only numbers");
    }
  }
}
