package com.clientservice.application.entity.domain;

import com.clientservice.application.ValidationExtension;
import com.clientservice.application.entity.command.CreateClientCommand;

import java.util.Collections;
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

  public Client(CreateClientCommand command) {
    this(
        UUID.randomUUID(),
        command.bankId,
        Collections.singletonList(command.passport),
        command.addresses,
        command.phoneNumber,
        command.email,
        command.source
    );
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
        .findFirst()
        .orElseThrow(() ->
            new IllegalStateException("Actual registration address not found for client id =" + id.toString())
        );
  }

  public Address getActualLivingAddress() {
    return addresses.stream()
        .filter(a -> !a.isDeprecated && a.type == AddressType.LIVING)
        .findFirst()
        .orElseThrow(() ->
            new IllegalStateException("Actual living address not found for client id =" + id.toString())
        );
  }

  private void validate() {
    if (id == null) throw new IllegalArgumentException("Identifier is mandatory");
    if (bankId == null) throw new IllegalArgumentException("Bank identifier is mandatory");

    validatePassports(passports);
    validateAddresses(addresses);

    if (ValidationExtension.isEmptyOrBlank(phoneNumber)) throw new IllegalArgumentException("Phone number is mandatory");
    if (ValidationExtension.isPhoneNumberValid(phoneNumber)) throw new IllegalArgumentException("Phone number isn't in valid format");
    if (ValidationExtension.isEmptyOrBlank(email)) throw new IllegalArgumentException("Email is mandatory");
    if (source == null) throw new IllegalArgumentException("Creation source is mandatory");
  }

  private void validateAddresses(List<Address> addresses) {
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

  private void validatePassports(List<Passport> passports) {
    if (passports == null) throw new IllegalArgumentException("Passport data is mandatory");

    List<Passport> actual = passports.stream().filter(p -> !p.isDeprecated).collect(Collectors.toList());

    if (actual.isEmpty()) throw new IllegalArgumentException("Actual passport is mandatory");
    if (actual.size() > 1) throw new IllegalArgumentException("Allowed only single actual passport");
  }
}
