package com.cortinovis.GameMarketPlace.domain.Exceptions.Filters;

import com.cortinovis.GameMarketPlace.domain.Exceptions.Types;
import lombok.Getter;

@Getter
public class RateLimitException extends RuntimeException{
  Types type;

  public RateLimitException(String message){
    super(message);
    this.type = Types.RATE_LIMIT_EXCEEDED;
  }
}
