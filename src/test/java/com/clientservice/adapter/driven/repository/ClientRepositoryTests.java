package com.clientservice.adapter.driven.repository;

import com.clientservice.adapter.driven.repository.client.ClientJpaEntity;
import com.clientservice.adapter.driven.repository.client.ClientJpaMapper;
import com.clientservice.adapter.driven.repository.client.ClientJpaRepository;
import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Address;
import com.clientservice.application.entity.domain.AddressType;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.entity.domain.CreationSource;
import com.clientservice.application.entity.domain.Passport;
import com.clientservice.application.entity.exception.NotFoundException;
import com.clientservice.application.port.ClientRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
@ActiveProfiles("repository-test")
public class ClientRepositoryTests {

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
              "some middle",
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
              "some middle",
              "family",
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
  ClientJpaEntity clientJpaEntity = ClientJpaMapper.mapToJpa(client);

  private final UUID clientId1 = UUID.randomUUID();
  private final Client client1 = new Client(
      clientId1,
      bankId,
      Arrays.asList(
          new Passport(
              UUID.randomUUID(),
              clientId1,
              null,
              "nameFirst",
              "some middle",
              null,
              null,
              null,
              false
          )
      ),
      emptyList(),
      "79998886655",
      "email",
      CreationSource.MOBILE
  );
  ClientJpaEntity clientJpaEntity1 = ClientJpaMapper.mapToJpa(client1);

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

  @Transactional
  @Test
  public void testFindByFirstName() {
    //Given
    FindClientCommand command = new FindClientCommand(
        "firstName",
        null,
        null,
        null,
        null
    );

    List<Client> expected = singletonList(client);

    //When
    clientJpaRepository.saveAll(Arrays.asList(clientJpaEntity, clientJpaEntity1));
    List<Client> actual = clientRepository.findAllByFields(command);

    //Then
    assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
  }

  @Transactional
  @Test
  public void testFindByMiddleName() {
    //Given
    FindClientCommand command = new FindClientCommand(
        null,
        "some middle",
        null,
        null,
        null
    );

    List<Client> expected = Arrays.asList(client, client1);

    //When
    clientJpaRepository.saveAll(Arrays.asList(clientJpaEntity, clientJpaEntity1));
    List<Client> actual = clientRepository.findAllByFields(command);

    //Then
    assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
  }

  @Transactional
  @Test
  public void testFindByFamilyName() {
    //Given
    FindClientCommand command = new FindClientCommand(
        null,
        null,
        "family",
        null,
        null
    );

    List<Client> expected = singletonList(client);

    //When
    clientJpaRepository.saveAll(Arrays.asList(clientJpaEntity, clientJpaEntity1));
    List<Client> actual = clientRepository.findAllByFields(command);

    //Then
    assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
  }

  @Transactional
  @Test
  public void testFindByPhoneNumber() {
    //Given
    FindClientCommand command = new FindClientCommand(
        null,
        null,
        null,
        "79998886655",
        null
    );

    List<Client> expected = singletonList(client1);

    //When
    clientJpaRepository.saveAll(Arrays.asList(clientJpaEntity, clientJpaEntity1));
    List<Client> actual = clientRepository.findAllByFields(command);

    //Then
    assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
  }

  @Transactional
  @Test
  public void testFindByEmail() {
    //Given
    FindClientCommand command = new FindClientCommand(
        null,
        null,
        null,
        null,
        "email"
    );

    List<Client> expected = singletonList(client1);

    //When
    clientJpaRepository.saveAll(Arrays.asList(clientJpaEntity, clientJpaEntity1));
    List<Client> actual = clientRepository.findAllByFields(command);

    //Then
    assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
  }

  @Transactional
  @Test
  public void testReturningEmptyListWhenNothingFoundByCommand() {
    //Given
    FindClientCommand command = easyRandom.nextObject(FindClientCommand.class);

    //When
    List<Client> actual = clientRepository.findAllByFields(command);

    //Then
    assertEquals(emptyList(), actual);
  }
}
