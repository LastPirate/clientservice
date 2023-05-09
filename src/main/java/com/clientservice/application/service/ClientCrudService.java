package com.clientservice.application.service;

import com.clientservice.application.entity.command.CreateClientCommand;
import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;

import java.util.UUID;

public interface ClientCrudService {
  UUID create(CreateClientCommand command);
  Client findById(UUID id);
  Client findByFields(FindClientCommand command);
}
