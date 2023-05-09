package com.clientservice.adapter.driven.repository.address;

import com.clientservice.application.entity.domain.AddressType;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(AddressJpaEntity.class)
public class AddressJpaStaticMetamodel {
  public static volatile SingularAttribute<AddressJpaEntity, UUID> id;
  public static volatile SingularAttribute<AddressJpaEntity, UUID> clientId;
  public static volatile SingularAttribute<AddressJpaEntity, String> country;
  public static volatile SingularAttribute<AddressJpaEntity, String> city;
  public static volatile SingularAttribute<AddressJpaEntity, String> street;
  public static volatile SingularAttribute<AddressJpaEntity, String> building;
  public static volatile SingularAttribute<AddressJpaEntity, Integer> apartment;
  public static volatile SingularAttribute<AddressJpaEntity, AddressType> type;
  public static volatile SingularAttribute<AddressJpaEntity, Boolean> isDeprecated;
}
