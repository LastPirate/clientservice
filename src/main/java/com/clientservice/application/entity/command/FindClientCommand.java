package com.clientservice.application.entity.command;

import com.clientservice.application.ValidationExtension;

public class FindClientCommand {
  public final String firstName;
  public final String middleName;
  public final String familyName;
  public final String phoneNumber;
  public final String email;

  public FindClientCommand(
      String firstName,
      String middleName,
      String familyName,
      String phoneNumber,
      String email
  ) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.familyName = familyName;
    this.phoneNumber = phoneNumber;
    this.email = email;

    validate();
  }

  private void validate() {
    boolean phoneNotConfigured = ValidationExtension.isEmptyOrBlank(phoneNumber);

    if (
        ValidationExtension.isEmptyOrBlank(firstName) &&
            ValidationExtension.isEmptyOrBlank(middleName) &&
            ValidationExtension.isEmptyOrBlank(familyName) &&
            ValidationExtension.isEmptyOrBlank(email) &&
            phoneNotConfigured
    ) throw new IllegalArgumentException("Cannot find a client: at least one of fields shall not be null");

    if (!phoneNotConfigured && ValidationExtension.isPhoneNumberValid(phoneNumber)) {
      throw new IllegalArgumentException("Phone number has not valid format");
    }
  }
}
