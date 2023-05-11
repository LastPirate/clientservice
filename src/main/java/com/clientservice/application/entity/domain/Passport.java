package com.clientservice.application.entity.domain;

import com.clientservice.application.ValidationExtension;

import java.time.LocalDate;
import java.util.UUID;

public class Passport {
  public final UUID id;
  public final UUID clientId;

  public final String number;

  public final String firstName;
  public final String middleName;
  public final String familyName;

  public final LocalDate birthDate;
  public final String birthCity;

  public final boolean isDeprecated;

  public Passport(
      UUID id,
      UUID clientId,
      String number,
      String firstName,
      String middleName,
      String familyName,
      LocalDate birthDate,
      String birthCity,
      boolean isDeprecated
  ) {
    this.id = id;
    this.clientId = clientId;
    this.number = number;
    this.firstName = firstName;
    this.middleName = middleName;
    this.familyName = familyName;
    this.birthDate = birthDate;
    this.birthCity = birthCity;
    this.isDeprecated = isDeprecated;
  }

  void validate(CreationSource source) {
    if (id == null) throw new IllegalArgumentException("Identifier is mandatory");
    if (clientId == null) throw new IllegalArgumentException("Client's identifier is mandatory");

    if ((source == CreationSource.MAIL || source == CreationSource.BANK || source == CreationSource.GOSUSLUGI) &&
        ValidationExtension.isEmptyOrBlank(firstName)) {
      throw new IllegalArgumentException("First name is mandatory");
    }

    if (source == CreationSource.BANK || source == CreationSource.GOSUSLUGI) {
      if (ValidationExtension.isEmptyOrBlank(middleName)) throw new IllegalArgumentException("Middle name is mandatory");
      if (ValidationExtension.isEmptyOrBlank(familyName)) throw new IllegalArgumentException("Family name is mandatory");

      if (ValidationExtension.isEmptyOrBlank(number)) throw new IllegalArgumentException("Passport number is mandatory");
      if (ValidationExtension.isPassportNumberValid(number)) throw new IllegalArgumentException("Passport number has not valid format");

      if (birthDate == null) throw new IllegalArgumentException("Birth date is mandatory");
    }

    if (source == CreationSource.GOSUSLUGI && ValidationExtension.isEmptyOrBlank(birthCity)) {
      throw new IllegalArgumentException("Birth city is mandatory");
    }
  }
}
