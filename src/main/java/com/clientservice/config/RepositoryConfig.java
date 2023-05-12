package com.clientservice.config;

import com.clientservice.adapter.driven.repository.client.ClientJpaRepository;
import com.clientservice.adapter.driven.repository.client.ClientRepositoryImpl;
import com.clientservice.application.port.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.clientservice.adapter.driven.repository")
@EnableJpaRepositories("com.clientservice.adapter.driven.repository")
public class RepositoryConfig {

  @Bean
  ClientRepository clientRepository(
      ClientJpaRepository clientJpaRepository
  ) {
    return new ClientRepositoryImpl(clientJpaRepository);
  }
}
