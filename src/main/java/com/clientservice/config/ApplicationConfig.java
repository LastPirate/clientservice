package com.clientservice.config;

import com.clientservice.application.port.ClientRepository;
import com.clientservice.application.service.ClientCrudService;
import com.clientservice.application.service.ClientCrudServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  ClientCrudService clientCrudService(
      ClientRepository clientRepository
  ) {
    return new ClientCrudServiceImpl(clientRepository);
  }
}
