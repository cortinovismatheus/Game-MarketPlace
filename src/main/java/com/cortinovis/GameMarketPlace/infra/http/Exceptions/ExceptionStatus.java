package com.cortinovis.GameMarketPlace.infra.http.Exceptions;

public enum ExceptionStatus {

  NOT_FOUND(404);

  private final int status;

  ExceptionStatus(int status) {
    this.status = status;
  }

  public int getStatus() {
    return status;
  }
}