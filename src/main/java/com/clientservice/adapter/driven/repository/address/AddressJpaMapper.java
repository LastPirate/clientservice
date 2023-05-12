package com.clientservice.adapter.driven.repository.address;

import com.clientservice.application.entity.domain.Address;

public class AddressJpaMapper {

  public static Address mapToDomain(AddressJpaEntity entity) {
    return new Address(
        entity.id,
        entity.clientId,
        entity.country,
        entity.city,
        entity.street,
        entity.building,
        entity.apartment,
        entity.type,
        entity.isDeprecated
    );
  }

  public static AddressJpaEntity mapToJpa(Address address) {
    return new AddressJpaEntity(
        address.id,
        address.clientId,
        address.country,
        address.city,
        address.street,
        address.building,
        address.apartment,
        address.type,
        address.isDeprecated
    );
  }
}
