package com.clientservice.adapter.driver.controller;

import com.clientservice.adapter.driver.controller.model.ClientModelMapper;
import com.clientservice.adapter.driver.controller.model.CreateClientRequest;
import com.clientservice.application.entity.domain.Client;
import com.clientservice.application.entity.domain.CreationSource;
import com.clientservice.application.service.ClientCrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static com.clientservice.adapter.driver.controller.model.ClientModelMapper.SOURCE_HEADER;
import static com.clientservice.adapter.driver.controller.model.ClientModelMapper.mapToClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientCrudController.class)
public class ClientCrudControllerTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ClientCrudService service;

  @ParameterizedTest
  @CsvSource({
      "MAIL",
      "MOBILE",
      "BANK",
      "GOSUSLUGI"
  })
  public void testCreateClient(CreationSource source) throws Exception {
    //Given
    PodamFactory factory = new PodamFactoryImpl();

    CreateClientRequest request = factory.manufacturePojo(CreateClientRequest.class);
    String content = objectMapper.writeValueAsString(request);
    Client client = mapToClient(source, request);

    //When
    MockedStatic<ClientModelMapper> clientMapper = Mockito.mockStatic(ClientModelMapper.class);
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

    clientMapper.close();
  }
}
