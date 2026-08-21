package com.cortinovis.GameMarketPlace.domain.valueObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price {
  private int value;

  public Price(int value){
    validation(value);
    this.value = value;
  }

  private void validation(int value){
    if(value <= 0){
      throw new IllegalArgumentException("Price must be greater than zero");
    }
  }
}
