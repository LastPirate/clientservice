package com.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "com.clientservice.adapter",
        "com.clientservice.config"
    }
)
public class ClientService {
  public static void main(String[] args) {
    SpringApplication.run(ClientService.class, args);
  }
}
