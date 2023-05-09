package com.clientservice.application.service;

import com.clientservice.application.entity.command.CreateClientCommand;
import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientCrudServiceImpl implements ClientCrudService {

  private final ClientRepository repository;

  @Autowired
  public ClientCrudServiceImpl(ClientRepository repository) {
    this.repository = repository;
  }

  @Override
  public UUID create(CreateClientCommand command) {
    Client newClient = new Client(command);

    return repository.create(newClient).id;
  }

  @Override
  public Client find(UUID id) {
    return repository.find(id);
  }

  @Override
  public Client find(FindClientCommand command) {
    return repository.find(command);
  }
}
