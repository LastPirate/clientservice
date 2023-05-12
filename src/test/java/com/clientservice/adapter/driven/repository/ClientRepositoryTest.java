package com.clientservice.adapter.driven.repository;

import com.clientservice.adapter.driven.repository.client.ClientJpaMapper;
import com.clientservice.adapter.driven.repository.client.ClientJpaRepository;
import com.clientservice.application.entity.domain.Address;
import com.clientservice.application.entity.domain.AddressType;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.entity.domain.CreationSource;
import com.clientservice.application.entity.domain.Passport;
import com.clientservice.application.port.ClientRepository;
import org.jeasy.random.EasyRandom;
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

  @Transactional
  @Test
  public void testSaveClient() {
    //Given
    UUID clientId = UUID.randomUUID();
    UUID bankId = UUID.randomUUID();
    Client client = new Client(
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

    //When
    clientRepository.save(client);
    Client actual = ClientJpaMapper.mapToDomain(
        clientJpaRepository.findById(client.id).get()
    );

    //Then
    assertThat(client).usingRecursiveComparison().isEqualTo(actual);
  }
}
