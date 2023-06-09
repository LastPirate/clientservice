package com.clientservice.application.service;

import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.port.ClientRepository;

import java.util.List;
import java.util.UUID;

public class ClientCrudServiceImpl implements ClientCrudService {

  private final ClientRepository repository;

  public ClientCrudServiceImpl(ClientRepository repository) {
    this.repository = repository;
  }

  @Override
  public UUID create(Client client) {
    return repository.save(client);
  }

  @Override
  public Client findById(UUID id) {
    return repository.findById(id);
  }

  @Override
  public List<Client> findAllByFields(FindClientCommand command) {
    return repository.findAllByFields(command);
  }
}
