package com.clientservice.adapter.driven.repository.passport;

import com.clientservice.application.entity.domain.Passport;

public class PassportJpaMapper {

  public static Passport mapToDomain(PassportJpaEntity entity) {
    return new Passport(
        entity.id,
        entity.clientId,
        entity.number,
        entity.firstName,
        entity.middleName,
        entity.familyName,
        entity.birthDate,
        entity.birthCity,
        entity.isDeprecated
    );
  }

  public static PassportJpaEntity mapToJpa(Passport passport) {
    return new PassportJpaEntity(
        passport.id,
        passport.clientId,
        passport.number,
        passport.firstName,
        passport.middleName,
        passport.familyName,
        passport.birthDate,
        passport.birthCity,
        passport.isDeprecated
    );
  }
}
