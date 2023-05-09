package com.clientservice.adapter.driven.repository.client;

import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.port.ClientRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class ClientRepositoryImpl implements ClientRepository {

  private final ClientJpaRepository jpaRepository;

  public ClientRepositoryImpl(ClientJpaRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Transactional
  @Override
  public UUID save(Client client) {
    ClientJpaEntity entity = ClientJpaMapper.mapToJpa(client);

    return jpaRepository.save(entity).id;
  }

  @Override
  public Client findById(UUID id) {
    ClientJpaEntity entity = jpaRepository.findById(id).orElseThrow();

    return ClientJpaMapper.mapToDomain(entity);
  }

  @Override
  public Client findByFields(FindClientCommand command) {
    ClientJpaEntity entity = jpaRepository.findById().orElseThrow();

    return ClientJpaMapper.mapToDomain(entity);
  }
}
