package com.clientservice.adapter.driven.repository;

import com.clientservice.adapter.driven.repository.client.ClientJpaEntity;
import com.clientservice.adapter.driven.repository.client.ClientJpaMapper;
import com.clientservice.adapter.driven.repository.client.ClientJpaRepository;
import com.clientservice.application.entity.domain.Address;
import com.clientservice.application.entity.domain.AddressType;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.entity.domain.CreationSource;
import com.clientservice.application.entity.domain.Passport;
import com.clientservice.application.entity.exception.NotFoundException;
import com.clientservice.application.port.ClientRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("repository-test")
public class ClientRepositoryTest {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientJpaRepository clientJpaRepository;

  private final EasyRandom easyRandom = new EasyRandom();

  private final UUID clientId = UUID.randomUUID();
  private final UUID bankId = UUID.randomUUID();
  private final Client client = new Client(
      clientId,
      bankId,
      Arrays.asList(
          new Passport(
              UUID.randomUUID(),
              clientId,
              null,
              "firstName",
              null,
              null,
              null,
              null,
              false
          ),
          new Passport(
              UUID.randomUUID(),
              clientId,
              null,
              "firstName0",
              null,
              null,
              null,
              null,
              true
          )
      ),
      Arrays.asList(
          new Address(
              UUID.randomUUID(),
              clientId,
              "country",
              "city",
              "street",
              "building",
              null,
              AddressType.REGISTRATION,
              false
          )
      ),
      easyRandom.nextObject(String.class),
      easyRandom.nextObject(String.class),
      CreationSource.MAIL
  );

  @Transactional
  @Test
  public void testSaveClient() {
    //When
    clientRepository.save(client);
    Client actual = ClientJpaMapper.mapToDomain(
        clientJpaRepository.findById(client.id).get()
    );

    //Then
    assertThat(client).usingRecursiveComparison().isEqualTo(actual);
  }

  @Transactional
  @Test
  public void testFindById() {
    //Given
    ClientJpaEntity clientJpaEntity = ClientJpaMapper.mapToJpa(client);

    //When
    clientJpaRepository.save(clientJpaEntity);
    Client actual = clientRepository.findById(client.id);

    //Then
    assertThat(client).usingRecursiveComparison().isEqualTo(actual);
  }

  @Transactional
  @Test
  public void testThrowingExceptionWhenNotFoundById() {
    //Given
    NotFoundException expected = new NotFoundException(
        "client with id = " + clientId + " doesn't exist"
    );

    //When
    NotFoundException actual = null;
    try {
      clientRepository.findById(client.id);
    } catch (NotFoundException e) {
      actual = e;
    }

    //Then
    assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
  }
}
