package com.clientservice.adapter.driver.controller;

import com.clientservice.adapter.driver.controller.model.ClientModelMapper;
import com.clientservice.adapter.driver.controller.model.ClientResponse;
import com.clientservice.adapter.driver.controller.model.CreateClientRequest;
import com.clientservice.adapter.driver.controller.model.FindClientRequest;
import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.entity.domain.CreationSource;
import com.clientservice.application.service.ClientCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.clientservice.adapter.driver.controller.model.ClientModelMapper.SOURCE_HEADER;

@RestController
public class ClientCrudController {

  private final ClientCrudService service;

  public ClientCrudController(ClientCrudService service) {
    this.service = service;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/create")
  public UUID create(@RequestHeader(SOURCE_HEADER) CreationSource source, @RequestBody CreateClientRequest request) {
    Client client = ClientModelMapper.mapToClient(source, request);

    return service.create(client);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/find/{id}")
  public ClientResponse findById(@PathVariable("id") UUID id) {
    Client client = service.findById(id);

    return ClientModelMapper.mapToResponse(client);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/find")
  public List<ClientResponse> findAllByFields(@RequestBody FindClientRequest request) {
    FindClientCommand command = ClientModelMapper.mapToCommand(request);

    return service.findAllByFields(command).stream()
        .map(ClientModelMapper::mapToResponse).collect(Collectors.toList());
  }
}
