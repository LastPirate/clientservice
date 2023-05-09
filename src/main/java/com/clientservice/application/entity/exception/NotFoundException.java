package com.clientservice.application.entity.exception;

public class NotFoundException extends RuntimeException {
  public final String reason;

  public NotFoundException(String reason) {
    this.reason = reason;
  }

  @Override
  public String getMessage() {
    return "Item not found: " + reason;
  }
}
