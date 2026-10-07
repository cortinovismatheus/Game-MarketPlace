package com.cortinovis.GameMarketPlace.domain.Exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

  Types type;

  public NotFoundException(String message) {
    super(message);
    this.type = Types.NOT_FOUND;
  }
}