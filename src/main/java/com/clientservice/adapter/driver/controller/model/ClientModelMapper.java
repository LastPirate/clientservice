package com.clientservice.adapter.driver.controller.model;

import com.clientservice.application.entity.command.CreateClientCommand;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.entity.domain.CreationSource;

public class ClientModelMapper {

  public static final String SOURCE_HEADER = "x-Source";

  public static CreateClientCommand mapToCommand(CreationSource source, CreateClientRequest request) {
    return new CreateClientCommand(
        request.bankId,
        request.passport,
        request.addresses,
        request.phoneNumber,
        request.email,
        source
    );
  }

  public static ClientResponse mapToResponse(Client client) {
    return new ClientResponse(
        client.id,
        client.bankId,
        client.getActualPassport(),
        client.phoneNumber,
        client.email,
        client.getActualRegistrationAddress(),
        client.getActualLivingAddress()
    );
  }
}
