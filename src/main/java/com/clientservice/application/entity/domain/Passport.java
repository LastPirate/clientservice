package com.clientservice.application.entity.domain;

import com.clientservice.application.ValidationExtension;

import java.time.LocalDate;
import java.util.UUID;

public class Passport {
  public final UUID id;

  public final String passportNumber;

  public final String firstName;
  public final String middleName;
  public final String familyName;

  public final LocalDate birthDate;
  public final String birthCity;

  public final boolean isDeprecated;

  public Passport(
      UUID id,
      String passportNumber,
      String firstName,
      String middleName,
      String familyName,
      LocalDate birthDate,
      String birthCity,
      Boolean isDeprecated
  ) {
    this.id = id;
    this.passportNumber = passportNumber;
    this.firstName = firstName;
    this.middleName = middleName;
    this.familyName = familyName;
    this.birthDate = birthDate;
    this.birthCity = birthCity;
    this.isDeprecated = isDeprecated;

    validate();
  }

  private void validate() {
    if (id == null) throw new IllegalArgumentException("Identifier is mandatory");

    //there is an option that client doesn't have middle name
    if (
        ValidationExtension.isEmptyOrBlank(passportNumber) ||
            ValidationExtension.isEmptyOrBlank(firstName) ||
            ValidationExtension.isEmptyOrBlank(familyName) ||
            birthDate == null ||
            ValidationExtension.isEmptyOrBlank(birthCity)
    ) throw new IllegalArgumentException("Passport data must be defined fully");

    if (ValidationExtension.isPassportNumberValid(passportNumber)) {
      throw new IllegalArgumentException("Passport number has not valid format");
    }
  }
}
