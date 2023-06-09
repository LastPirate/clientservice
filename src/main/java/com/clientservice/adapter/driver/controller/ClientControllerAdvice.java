package com.clientservice.adapter.driver.controller;

import com.clientservice.application.entity.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ClientControllerAdvice {

  @ExceptionHandler(value = {
      IllegalArgumentException.class,
      IllegalStateException.class,
      NotFoundException.class
  })
  protected ResponseEntity<String> handleConflict(RuntimeException ex, WebRequest request) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
  }
}
