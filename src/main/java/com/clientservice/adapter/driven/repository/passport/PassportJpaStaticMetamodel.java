package com.clientservice.adapter.driven.repository.passport;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.util.UUID;

@StaticMetamodel(PassportJpaEntity.class)
public class PassportJpaStaticMetamodel {
  public static volatile SingularAttribute<PassportJpaEntity, UUID> id;
  public static volatile SingularAttribute<PassportJpaEntity, UUID> clientId;
  public static volatile SingularAttribute<PassportJpaEntity, String> number;
  public static volatile SingularAttribute<PassportJpaEntity, String> firstName;
  public static volatile SingularAttribute<PassportJpaEntity, String> middleName;
  public static volatile SingularAttribute<PassportJpaEntity, String> familyName;
  public static volatile SingularAttribute<PassportJpaEntity, LocalDate> birthDate;
  public static volatile SingularAttribute<PassportJpaEntity, String> birthCity;
  public static volatile SingularAttribute<PassportJpaEntity, Boolean> isDeprecated;
}
