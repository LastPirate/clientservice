package com.clientservice.application.entity.command;

import com.clientservice.application.entity.domain.Address;
import com.clientservice.application.entity.domain.CreationSource;
import com.clientservice.application.entity.domain.Passport;

import java.util.List;
import java.util.UUID;

public class CreateClientCommand {
  public final UUID bankId;

  public final Passport passport;
  public final List<Address> addresses;

  public final String phoneNumber;
  public final String email;

  public final CreationSource creationSource;

  public CreateClientCommand(
      UUID bankId,
      Passport passport,
      List<Address> addresses,
      String phoneNumber,
      String email,
      CreationSource creationSource
  ) {
    this.bankId = bankId;
    this.passport = passport;
    this.addresses = addresses;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.creationSource = creationSource;
  }
}
