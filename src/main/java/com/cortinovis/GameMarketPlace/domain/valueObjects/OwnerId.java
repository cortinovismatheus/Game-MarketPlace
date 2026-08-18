package com.cortinovis.GameMarketPlace.domain.valueObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerId {
  private final int value;

  public OwnerId(int value) {
    validate(value);
    this.value = value;
  }

  private void validate(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException("Owner ID must be greater than zero");
    }
  }

  public int getValue() {
    return value;
  }
}
