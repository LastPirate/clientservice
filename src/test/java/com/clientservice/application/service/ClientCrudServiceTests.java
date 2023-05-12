package com.clientservice.application.service;

import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.port.ClientRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClientCrudServiceTests {

  @InjectMocks
  private ClientCrudServiceImpl clientCrudService;

  @Mock
  private ClientRepository repository;

  private final EasyRandom easyRandom = new EasyRandom();

  @Test
  public void testCreateNewClient() {
    //Given
    Client client = easyRandom.nextObject(Client.class);

    //When
    doReturn(client.id).when(repository).save(any());

    UUID actual = clientCrudService.create(client);

    //Then
    assertEquals(client.id, actual);

    verify(repository, times(1)).save(client);
  }

  @Test
  public void testFindById() {
    //Given
    Client client = easyRandom.nextObject(Client.class);

    //When
    doReturn(client).when(repository).findById(any());

    Client actual = clientCrudService.findById(client.id);

    //Then
    assertThat(client).usingRecursiveComparison().isEqualTo(actual);

    verify(repository, times(1)).findById(client.id);
  }

  @Test
  public void testFindByCommand() {
    //Given
    FindClientCommand command = easyRandom.nextObject(FindClientCommand.class);
    List<Client> clients = easyRandom.objects(Client.class, 5).collect(Collectors.toList());

    //When
    doReturn(clients).when(repository).findAllByFields(any());

    List<Client> actual = clientCrudService.findAllByFields(command);

    //Then
    assertThat(clients).usingRecursiveComparison().isEqualTo(actual);

    verify(repository, times(1)).findAllByFields(command);
  }
}
