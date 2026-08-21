package com.cortinovis.GameMarketPlace.domain.valueObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserName {
  private String value;

  public UserName(String value){
    validations(value);
    this.value = value;
  }

  private void validations(String value){
    if(value == null){
      throw new IllegalArgumentException("Name cannot be null");
    }

    if(value.isBlank()){
      throw new IllegalArgumentException("Name cannot be is blank");
    }

    if(value.length() < 4){
      throw new IllegalArgumentException("Name must have a 4 characters");
    }

    if (value.length() > 30) {
      throw new IllegalArgumentException("Username must have at most 30 characters");
    }

    if(value.matches(".*[0-9].*")){
      throw new IllegalArgumentException("The name cannot contain numbers");
    }
  }
}
