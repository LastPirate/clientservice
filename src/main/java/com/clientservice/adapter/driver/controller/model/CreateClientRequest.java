package com.clientservice.adapter.driver.controller.model;

import com.clientservice.application.entity.domain.Address;
import com.clientservice.application.entity.domain.Passport;

import java.util.List;
import java.util.UUID;

public class CreateClientRequest {
  public final UUID bankId;

  public final Passport passport;
  public final List<Address> addresses;

  public final String phoneNumber;
  public final String email;

  public CreateClientRequest(
      UUID bankId,
      Passport passport,
      List<Address> addresses,
      String phoneNumber,
      String email
  ) {
    this.bankId = bankId;
    this.passport = passport;
    this.addresses = addresses;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }
}
