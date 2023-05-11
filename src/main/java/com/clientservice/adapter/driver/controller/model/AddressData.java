package com.clientservice.adapter.driver.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressData {
  public final String country;
  public final String city;
  public final String street;
  public final String building;
  public final int apartment;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public AddressData(
      @JsonProperty("country") String country,
      @JsonProperty("city") String city,
      @JsonProperty("street") String street,
      @JsonProperty("building") String building,
      @JsonProperty("apartment") int apartment
  ) {
    this.country = country;
    this.city = city;
    this.street = street;
    this.building = building;
    this.apartment = apartment;
  }
}
