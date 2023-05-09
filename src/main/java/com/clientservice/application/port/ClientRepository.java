package com.clientservice.application.port;

import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;

import java.util.UUID;

public interface ClientRepository {
  UUID save(Client client);
  Client findById(UUID id);
  Client findByFields(FindClientCommand command);
}
