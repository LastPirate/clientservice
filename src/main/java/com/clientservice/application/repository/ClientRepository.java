package com.clientservice.application.repository;

import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;

import java.util.UUID;


public interface ClientRepository {
  Client create(Client client);
  Client find(UUID id);
  Client find(FindClientCommand command);
}
