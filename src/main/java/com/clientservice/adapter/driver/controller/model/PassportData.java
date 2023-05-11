package com.clientservice.adapter.driver.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class PassportData {
  public final String number;

  public final String firstName;
  public final String middleName;
  public final String familyName;

  public final LocalDate birthDate;
  public final String birthCity;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public PassportData(
      @JsonProperty("number") String number,
      @JsonProperty("firstName") String firstName,
      @JsonProperty("middleName") String middleName,
      @JsonProperty("familyName") String familyName,
      @JsonProperty("birthDate") LocalDate birthDate,
      @JsonProperty("birthCity") String birthCity
  ) {
    this.number = number;
    this.firstName = firstName;
    this.middleName = middleName;
    this.familyName = familyName;
    this.birthDate = birthDate;
    this.birthCity = birthCity;
  }
}
