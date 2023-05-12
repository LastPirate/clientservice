package com.clientservice.adapter.driver.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FindClientRequest {
  public final String firstName;
  public final String middleName;
  public final String familyName;
  public final String phoneNumber;
  public final String email;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public FindClientRequest(
      @JsonProperty("firstName") String firstName,
      @JsonProperty("middleName") String middleName,
      @JsonProperty("familyName") String familyName,
      @JsonProperty("phoneNumber") String phoneNumber,
      @JsonProperty("email") String email
  ) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.familyName = familyName;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }
}
