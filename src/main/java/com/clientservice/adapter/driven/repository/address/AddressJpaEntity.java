package com.clientservice.adapter.driven.repository.address;

import com.clientservice.application.entity.domain.AddressType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(
    name = "address",
    schema = "client_service"
)
public class AddressJpaEntity {

  @Id
  @Column(name = "id")
  public UUID id;

  @Column(name = "client_id")
  public UUID clientId;

  @Column(name = "country")
  public String country;

  @Column(name = "city")
  public String city;

  @Column(name = "street")
  public String street;

  @Column(name = "building")
  public String building;

  @Column(name = "apartment")
  public int apartment;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  public AddressType type;

  @Column(name = "is_deprecated")
  public boolean isDeprecated;

  public AddressJpaEntity() {
  }

  public AddressJpaEntity(
      UUID id,
      UUID clientId,
      String country,
      String city,
      String street,
      String building,
      int apartment,
      AddressType type,
      boolean isDeprecated
  ) {
    this.id = id;
    this.clientId = clientId;
    this.country = country;
    this.city = city;
    this.street = street;
    this.building = building;
    this.apartment = apartment;
    this.type = type;
    this.isDeprecated = isDeprecated;
  }
}
