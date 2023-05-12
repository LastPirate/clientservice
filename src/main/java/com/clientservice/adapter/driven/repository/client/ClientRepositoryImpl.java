package com.clientservice.adapter.driven.repository.client;

import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.entity.exception.NotFoundException;
import com.clientservice.application.port.ClientRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    ClientJpaEntity entity = jpaRepository.findById(id).orElseThrow(() ->
        new NotFoundException("client with id = " + id + " doesn't exist")
    );

    return ClientJpaMapper.mapToDomain(entity);
  }

  @Override
  public List<Client> findAllByFields(FindClientCommand command) {
    Specification<ClientJpaEntity> specification = new ClientJpaSpecification(command);
    List<ClientJpaEntity> entities = jpaRepository.findAll(specification);

    return entities.stream()
        .map(ClientJpaMapper::mapToDomain).collect(Collectors.toList());
  }
}
