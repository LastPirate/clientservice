package com.clientservice.adapter.driven.repository.passport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "passport")
public class PassportJpaEntity {

  @Id
  @Column(name = "id")
  public UUID id;

  @Column(name = "client_id")
  public UUID clientId;

  @Column(name = "number")
  public String number;

  @Column(name = "first_name")
  public String firstName;

  @Column(name = "middle_name")
  public String middleName;

  @Column(name = "family_name")
  public String familyName;

  @Column(name = "birth_date")
  public LocalDate birthDate;

  @Column(name = "birth_city")
  public String birthCity;

  @Column(name = "is_deprecated")
  public boolean isDeprecated;

  public PassportJpaEntity() {
  }

  public PassportJpaEntity(
      UUID id,
      UUID clientId,
      String number,
      String firstName,
      String middleName,
      String familyName,
      LocalDate birthDate,
      String birthCity,
      boolean isDeprecated
  ) {
    this.id = id;
    this.clientId = clientId;
    this.number = number;
    this.firstName = firstName;
    this.middleName = middleName;
    this.familyName = familyName;
    this.birthDate = birthDate;
    this.birthCity = birthCity;
    this.isDeprecated = isDeprecated;
  }
}
