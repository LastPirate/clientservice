package com.clientservice.adapter.driven.repository.client;

import com.clientservice.adapter.driven.repository.address.AddressJpaMapper;
import com.clientservice.adapter.driven.repository.passport.PassportJpaMapper;
import com.clientservice.application.entity.domain.Client;

import java.util.stream.Collectors;

public class ClientJpaMapper {

  public static Client mapToDomain(ClientJpaEntity entity) {
    return new Client(
      entity.id,
      entity.bankId,
      entity.passports.stream().map(PassportJpaMapper::mapToDomain).collect(Collectors.toList()),
      entity.addresses.stream().map(AddressJpaMapper::mapToDomain).collect(Collectors.toList()),
      entity.phoneNumber,
      entity.email,
      entity.source
    );
  }

  public static ClientJpaEntity mapToJpa(Client client) {
    return new ClientJpaEntity(
        client.id,
        client.bankId,
        client.passports.stream().map(PassportJpaMapper::mapToJpa).collect(Collectors.toList()),
        client.addresses.stream().map(AddressJpaMapper::mapToJpa).collect(Collectors.toList()),
        client.phoneNumber,
        client.email,
        client.source
    );
  }
}
