package com.app.clients.infrastucture;

import com.app.clients.application.usecases.ClientsUseCase;
import com.app.clients.domain.entities.Clients;
import com.app.clients.infrastructure.controllers.ClientsController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientsControllerTest {

    @Mock
    private ClientsUseCase clientsUseCase;

    @InjectMocks
    private ClientsController clientsController;


    @Test
    public void testGetAllClients_success() {
        // Mock the behavior of clientsUseCase.getAllClients
        Page<Clients> mockPage = Page.empty();
        when(clientsUseCase.getAllClients(0, 50)).thenReturn(mockPage);

        // Call the controller method
        ResponseEntity<Object> response = clientsController.allClients(0, 50);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Page);
    }

    @Test
    public void testDeleteClient_success() {
        // No need to mock anything for this test

        // Call the controller method
        ResponseEntity<Void> response = clientsController.deleteClient("someSharedKey");

        // Assert the response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}