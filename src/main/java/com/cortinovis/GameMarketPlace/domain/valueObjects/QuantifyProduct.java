package com.cortinovis.GameMarketPlace.domain.valueObjects;

import lombok.Getter;

@Getter
public class QuantifyProduct {
  private Integer value;

  public QuantifyProduct(Integer value){
    validations(value);
    this.value = value;
  }

  private void validations(Integer value){

    if(value == null){
      throw new IllegalArgumentException("Quantify product cannot be null");
    }

    if (value <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than zero");
    }
  }
}

