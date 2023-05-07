package com.clientservice.application.entity.domain;

import com.clientservice.application.ValidationExtension;

import java.util.UUID;

public class Address {
  public final UUID id;

  public final String country;
  public final String city;
  public final String street;
  public final String building;
  public final int apartment;

  public final AddressType type;
  public final boolean isDeprecated;

  public Address(
      UUID id,
      String country,
      String city,
      String street,
      String building,
      Integer apartment,
      AddressType type,
      Boolean isDeprecated
  ) {
    this.id = id;
    this.country = country;
    this.city = city;
    this.street = street;
    this.building = building;
    this.apartment = apartment;
    this.type = type;
    this.isDeprecated = isDeprecated;

    validate();
  }

  private void validate() {
    if (id == null) throw new IllegalArgumentException("Identifier is mandatory");
    if (type == null) throw new IllegalArgumentException("Address type is mandatory");

    if (
        ValidationExtension.isEmptyOrBlank(country) ||
            ValidationExtension.isEmptyOrBlank(city) ||
            ValidationExtension.isEmptyOrBlank(street) ||
            ValidationExtension.isEmptyOrBlank(building)
    ) throw new IllegalArgumentException("Address must be defined fully");
  }
}
