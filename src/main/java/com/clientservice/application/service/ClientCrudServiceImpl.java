package com.clientservice.application.service;

import com.clientservice.application.entity.command.CreateClientCommand;
import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;

import java.util.UUID;

public class ClientCrudServiceImpl implements ClientCrudService {

  @Override
  public Client create(CreateClientCommand command) {
    return null;
  }

  @Override
  public Client find(UUID id) {
    return null;
  }

  @Override
  public Client find(FindClientCommand command) {
    return null;
  }
}
