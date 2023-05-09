package com.clientservice.adapter.driven.repository.client;

import com.clientservice.adapter.driven.repository.address.AddressJpaEntity;
import com.clientservice.adapter.driven.repository.passport.PassportJpaEntity;
import com.clientservice.application.entity.domain.CreationSource;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(ClientJpaEntity.class)
public class ClientJpaStaticMetamodel {
  public static volatile SingularAttribute<ClientJpaEntity, UUID> id;
  public static volatile SingularAttribute<ClientJpaEntity, UUID> bankId;
  public static volatile CollectionAttribute<ClientJpaEntity, PassportJpaEntity> passports;
  public static volatile CollectionAttribute<ClientJpaEntity, AddressJpaEntity> addresses;
  public static volatile SingularAttribute<ClientJpaEntity, String> phoneNumber;
  public static volatile SingularAttribute<ClientJpaEntity, String> email;
  public static volatile SingularAttribute<ClientJpaEntity, CreationSource> source;
}
