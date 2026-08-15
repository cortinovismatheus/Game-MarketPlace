package com.cortinovis.GameMarketPlace.domain.valueObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {
  private final String value;

  public Email(String value) {
    validation(value);
    this.value = value;
  }

  private void validation(String email){
    if (email == null) {
      throw new IllegalArgumentException("Email cannot be null");
    }

    if (email.isBlank()) {
      throw new IllegalArgumentException("Email cannot be empty");
    }

    if(!email.contains("@")){
      throw new IllegalArgumentException("Invalid email");
    }

    if(!email.contains(".")){
      throw new IllegalArgumentException("Invalid email");
    }

    if(email.indexOf("@") != email.lastIndexOf("@")){
      throw new IllegalArgumentException("Invalid email");
    }

    if(email.startsWith("@")){
      throw new IllegalArgumentException("Invalid email");
    }

    if(email.endsWith("@")){
      throw new IllegalArgumentException("Invalid email");
    }

    int at = email.indexOf("@");
    int dot = email.indexOf(".");

    if (dot < at || dot == at + 1) {
      throw new IllegalArgumentException("Invalid email");
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Email other)) {
      return false;
    }

    return value.equals(other.getValue());
  }
}
