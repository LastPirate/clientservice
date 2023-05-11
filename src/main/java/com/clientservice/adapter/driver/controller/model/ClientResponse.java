package com.clientservice.adapter.driver.controller.model;

import com.clientservice.application.entity.domain.Address;
import com.clientservice.application.entity.domain.Passport;

import java.util.UUID;

public class ClientResponse {
  public final UUID id;
  public final UUID bankId;

  public final Passport passport;

  public final String phoneNumber;
  public final String email;

  public final Address registrationAddress;
  public final Address livingAddress;

  public ClientResponse(
      UUID id,
      UUID bankId,
      Passport passport,
      String phoneNumber,
      String email,
      Address registrationAddress,
      Address livingAddress
  ) {
    this.id = id;
    this.bankId = bankId;
    this.passport = passport;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.registrationAddress = registrationAddress;
    this.livingAddress = livingAddress;
  }
}
