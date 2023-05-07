package com.clientservice.application.service;

import com.clientservice.application.entity.command.CreateClientCommand;
import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;

import java.util.UUID;

public interface ClientCrudService {
  Client create(CreateClientCommand command);
  Client find(UUID id);
  Client find(FindClientCommand command);
}
