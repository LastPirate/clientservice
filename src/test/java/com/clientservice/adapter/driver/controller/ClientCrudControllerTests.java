package com.clientservice.adapter.driver.controller;

import com.clientservice.adapter.driver.controller.model.ClientModelMapper;
import com.clientservice.adapter.driver.controller.model.ClientResponse;
import com.clientservice.adapter.driver.controller.model.CreateClientRequest;
import com.clientservice.adapter.driver.controller.model.FindClientRequest;
import com.clientservice.application.entity.command.FindClientCommand;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.entity.domain.CreationSource;
import com.clientservice.application.service.ClientCrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.clientservice.adapter.driver.controller.model.ClientModelMapper.SOURCE_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientCrudController.class)
public class ClientCrudControllerTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ClientCrudService service;

  private final EasyRandom easyRandom = new EasyRandom();
  private final MockedStatic<ClientModelMapper> clientMapper = Mockito.mockStatic(ClientModelMapper.class);

  @BeforeEach
  void before() {
    clientMapper.when(() -> ClientModelMapper.mapToResponse(any())).thenCallRealMethod();
    clientMapper.when(() -> ClientModelMapper.mapToCommand(any())).thenCallRealMethod();
  }

  @AfterEach
  void after() {
    clientMapper.close();
  }

  @ParameterizedTest
  @CsvSource({
      "MAIL",
      "MOBILE",
      "BANK",
      "GOSUSLUGI"
  })
  public void testCreateClient(CreationSource source) throws Exception {
    //Given
    Client client = easyRandom.nextObject(Client.class);
    CreateClientRequest request = easyRandom.nextObject(CreateClientRequest.class);
    String content = objectMapper.writeValueAsString(request);

    //When
    clientMapper.when(() -> ClientModelMapper.mapToClient(any(), any())).thenReturn(client);
    doReturn(client.id).when(service).create(any());

    MockHttpServletResponse actual = mvc.perform(
        post("/create")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8)
            .content(content)
            .header(SOURCE_HEADER, source)
    ).andReturn().getResponse();

    //Then
    assertEquals(HttpStatus.CREATED.value(), actual.getStatus());
    assertEquals(client.id, objectMapper.readValue(actual.getContentAsString(), UUID.class));

    verify(service, times(1)).create(refEq(client));
  }

  @Test
  public void testFindClientById() throws Exception {
    //Given
    Client client = easyRandom.nextObject(Client.class);

    String expected = objectMapper.writeValueAsString(
        ClientModelMapper.mapToResponse(client)
    );

    //When
    doReturn(client).when(service).findById(any());

    MockHttpServletResponse actual = mvc.perform(
        get("/find/" + client.id)
    ).andReturn().getResponse();

    //Then
    assertEquals(HttpStatus.OK.value(), actual.getStatus());
    assertEquals(expected, actual.getContentAsString());

    verify(service, times(1)).findById(client.id);
  }

  @Test
  public void testFindAllByField() throws Exception {
    //Given
    FindClientRequest request = easyRandom.nextObject(FindClientRequest.class);
    String content = objectMapper.writeValueAsString(request);
    FindClientCommand command = ClientModelMapper.mapToCommand(request);
    List<Client> clients = easyRandom.objects(Client.class, 3)
        .collect(Collectors.toList());
    List<ClientResponse> response = clients.stream()
        .map(ClientModelMapper::mapToResponse).collect(Collectors.toList());

    String expected = objectMapper.writeValueAsString(response);

    //When
    doReturn(clients).when(service).findAllByFields(any());

    MockHttpServletResponse actual = mvc.perform(
        get("/find")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8)
            .content(content)
    ).andReturn().getResponse();

    //Then
    assertEquals(HttpStatus.OK.value(), actual.getStatus());
    assertEquals(expected, actual.getContentAsString());

    verify(service, times(1)).findAllByFields(refEq(command));
  }
}
