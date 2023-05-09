package com.clientservice.adapter.driver.controller;

import com.clientservice.application.entity.command.CreateClientCommand;
import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.service.ClientCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ClientCrudController {

  private final ClientCrudService service;

  public ClientCrudController(ClientCrudService service) {
    this.service = service;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/create")
  public UUID create(@RequestBody CreateClientCommand command) {
    return service.create(command);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/find/{id}")
  public Client findById(@PathVariable("id") UUID id) {
    return service.findById(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/find")
  public List<Client> findAllByFields(@RequestBody FindClientCommand command) {
    return service.findAllByFields(command);
  }
}
