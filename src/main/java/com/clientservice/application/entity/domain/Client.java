package com.clientservice.application.entity.domain;

import com.clientservice.application.ValidationExtension;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Client {
  public final UUID id;
  public final UUID bankId;

  public final List<Passport> passports;
  public final List<Address> addresses;

  public final String phoneNumber;
  public final String email;

  public final CreationSource source;

  public Client(
      UUID id,
      UUID bankId,
      List<Passport> passports,
      List<Address> addresses,
      String phoneNumber,
      String email,
      CreationSource source
  ) {
    this.id = id;
    this.bankId = bankId;
    this.passports = passports;
    this.addresses = addresses;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.source = source;

    validate();
  }

  public Passport getActualPassport() {
    return passports.stream()
        .filter(p -> !p.isDeprecated)
        .findFirst()
        .orElseThrow(() ->
            new IllegalStateException("Actual passport not found for client id =" + id.toString())
        );
  }

  public Address getActualRegistrationAddress() {
    return addresses.stream()
        .filter(a -> !a.isDeprecated && a.type == AddressType.REGISTRATION)
        .findFirst().orElse(null);
  }

  public Address getActualLivingAddress() {
    return addresses.stream()
        .filter(a -> !a.isDeprecated && a.type == AddressType.LIVING)
        .findFirst().orElse(null);
  }

  private void validate() {
    if (id == null) throw new IllegalArgumentException("Identifier is mandatory");
    if (source == null) throw new IllegalArgumentException("Creation source is mandatory");

    if (source == CreationSource.MOBILE || source == CreationSource.GOSUSLUGI) {
      if (ValidationExtension.isEmptyOrBlank(phoneNumber)) throw new IllegalArgumentException("Phone number is mandatory");
      if (ValidationExtension.isPhoneNumberValid(phoneNumber)) throw new IllegalArgumentException("Phone number isn't in valid format");
    }

    validateActualPassport(passports);
    getActualPassport().validate(source);

    if ((source == CreationSource.MAIL || source == CreationSource.GOSUSLUGI) &&
        ValidationExtension.isEmptyOrBlank(email)) {
      throw new IllegalArgumentException("Email is mandatory");
    }

    if ((source == CreationSource.BANK || source == CreationSource.GOSUSLUGI) &&
        bankId == null) {
      throw new IllegalArgumentException("Bank identifier is mandatory");
    }

    if (source == CreationSource.GOSUSLUGI) {
      validateActualAddresses(addresses);
      getActualLivingAddress().validate();
    }
  }

  private void validateActualAddresses(List<Address> addresses) {
    if (addresses == null) throw new IllegalArgumentException("Address data is mandatory");

    if (addresses.size() == 1) {
      Address address = addresses.get(0);

      if (address.isDeprecated || address.type != AddressType.REGISTRATION) {
        throw new IllegalArgumentException("Actual registration address is mandatory");
      }
    } else {
      List<Address> registrations = addresses.stream()
          .filter(a -> !a.isDeprecated && a.type == AddressType.REGISTRATION).collect(Collectors.toList());

      if (registrations.isEmpty()) throw new IllegalArgumentException("Actual registration address is mandatory");
      if (registrations.size() > 1) throw new IllegalArgumentException("Allowed only single actual registration address");

      List<Address> livings = addresses.stream()
          .filter(a -> !a.isDeprecated && a.type == AddressType.LIVING).collect(Collectors.toList());

      if (livings.size() > 1) throw new IllegalArgumentException("Allowed only single actual living address");
    }
  }

  private void validateActualPassport(List<Passport> passports) {
    if (passports == null) throw new IllegalArgumentException("Passport data is mandatory");

    List<Passport> actual = passports.stream().filter(p -> !p.isDeprecated).collect(Collectors.toList());

    if (actual.isEmpty()) throw new IllegalArgumentException("Actual passport is mandatory");
    if (actual.size() > 1) throw new IllegalArgumentException("Allowed only single actual passport");
  }
}
