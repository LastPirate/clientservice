package com.clientservice.adapter.driven.repository.client;

import com.clientservice.adapter.driven.repository.address.AddressJpaEntity;
import com.clientservice.adapter.driven.repository.passport.PassportJpaEntity;
import com.clientservice.application.entity.domain.CreationSource;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
    name = "client",
    schema = "client_service"
)
public class ClientJpaEntity {

  @Id
  @Column(name = "id")
  public UUID id;

  @Column(name = "bank_id")
  public UUID bankId;

  @OneToMany(
      mappedBy = "clientId",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  public List<PassportJpaEntity> passports;

  @OneToMany(
      mappedBy = "clientId",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  public List<AddressJpaEntity> addresses;

  @Column(name = "phone_number")
  public String phoneNumber;

  @Column(name = "email")
  public String email;

  @Column(name = "source")
  @Enumerated(EnumType.STRING)
  public CreationSource source;

  public ClientJpaEntity() {
  }

  public ClientJpaEntity(
      UUID id,
      UUID bankId,
      List<PassportJpaEntity> passports,
      List<AddressJpaEntity> addresses,
      String phoneNumber,
      String email,
      CreationSource source
  ) {
    this.id = id;
    this.bankId = bankId;
    this.passports = passports;
    this.addresses = addresses;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.source = source;
  }
}
