package com.clientservice.adapter.driver.controller.model;

import com.clientservice.application.entity.domain.Address;
import com.clientservice.application.entity.domain.AddressType;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.entity.domain.CreationSource;
import com.clientservice.application.entity.domain.Passport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientModelMapper {

  public static final String SOURCE_HEADER = "x-Source";

  public static Client mapToClient(CreationSource source, CreateClientRequest request) {
    UUID clientId = UUID.randomUUID();

    List<Passport> domainPassports = new ArrayList<>();
    domainPassports.add(mapToPassport(clientId, request.passport));

    List<Address> domainAddresses = new ArrayList<>();
    domainAddresses.add(mapToAddress(clientId, AddressType.REGISTRATION, request.registrationAddress));
    domainAddresses.add(mapToAddress(clientId, AddressType.LIVING, request.livingAddress));

    return new Client(
        clientId,
        request.bankId,
        domainPassports,
        domainAddresses,
        request.phoneNumber,
        request.email,
        source
    );
  }

  private static Passport mapToPassport(UUID clientId, PassportData passportData) {
    return new Passport(
        UUID.randomUUID(),
        clientId,
        passportData.number,
        passportData.firstName,
        passportData.middleName,
        passportData.familyName,
        passportData.birthDate,
        passportData.birthCity,
        false
    );
  }

  private static Address mapToAddress(UUID clientId, AddressType type, AddressData addressData) {
    return new Address(
        UUID.randomUUID(),
        clientId,
        addressData.country,
        addressData.city,
        addressData.street,
        addressData.building,
        addressData.apartment,
        type,
        false
    );
  }


  public static ClientResponse mapToResponse(Client client) {
    return new ClientResponse(
        client.id,
        client.bankId,
        client.getActualPassport(),
        client.phoneNumber,
        client.email,
        client.getActualRegistrationAddress(),
        client.getActualLivingAddress()
    );
  }
}
