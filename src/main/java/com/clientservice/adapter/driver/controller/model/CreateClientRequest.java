package com.clientservice.adapter.driver.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateClientRequest {
  public final UUID bankId;

  public final PassportData passport;
  public final AddressData registrationAddress;
  public final AddressData livingAddress;

  public final String phoneNumber;
  public final String email;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public CreateClientRequest(
      @JsonProperty("bankId") UUID bankId,
      @JsonProperty("passport") PassportData passport,
      @JsonProperty("registrationAddress") AddressData registrationAddress,
      @JsonProperty("livingAddress") AddressData livingAddress,
      @JsonProperty("phoneNumber") String phoneNumber,
      @JsonProperty("email") String email
  ) {
    this.bankId = bankId;
    this.passport = passport;
    this.registrationAddress = registrationAddress;
    this.livingAddress = livingAddress;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }
}
