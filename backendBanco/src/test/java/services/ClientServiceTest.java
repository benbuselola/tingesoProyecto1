package com.example.backendBanco.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.backendBanco.entities.ClientEntity;
import com.example.backendBanco.repositories.ClientRepository;
import com.example.backendBanco.services.ClientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getClients() {
        List<ClientEntity> clients = clientService.getClients();
        verify(clientRepository).findAll();
    }

    @Test
    public void saveClient() {
        ClientEntity client = new ClientEntity(3L, "12345678-9", "Juan", "Perez", 18, "", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findByRut(client.getRut())).thenReturn(null);
        when(clientRepository.save(client)).thenReturn(client);
        ClientEntity savedClient = clientService.saveClient(client);
        assertThat(savedClient).isEqualTo(client);
    }
    @Test
    public void saveClientUnderAge() {
        ClientEntity client = new ClientEntity(3L, "12345678-9", "Juan", "Perez", 17, "", "12345678", "Calle Falsa 123", "1234");
        ClientEntity savedClient = clientService.saveClient(client);
        assertThat(savedClient).isNull();
    }
    @Test
    public void getClientById() {
        ClientEntity client = new ClientEntity(3L, "12345678-9", "Juan", "Perez", 17, "", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findById(3L)).thenReturn(java.util.Optional.of(client));
        ClientEntity clientFound = clientService.getClientById(3L);
        assertThat(clientFound).isEqualTo(client);
    }
    @Test
    public void getClientByIdNotFound() {
        ClientEntity client = new ClientEntity(3L, "12345678-9", "Juan", "Perez", 17, "", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findByRut("12345672-9")).thenReturn(null);
        ClientEntity clientFound = clientService.getClientByRut("12345678-0");
        assertThat(clientFound).isNull();
    }
    @Test
    public void getClientByRut() {
        ClientEntity client = new ClientEntity(3L, "12345678-9", "Juan", "Perez", 17, "", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findByRut("12345678-9")).thenReturn(client);
        ClientEntity clientFound = clientService.getClientByRut("12345678-9");
        assertThat(clientFound).isEqualTo(client);
    }
    @Test
    public void getClientByRutNotFound() {
        ClientEntity client = new ClientEntity(3L, "12345678-9", "Juan", "Perez", 17, "", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findByRut("12345672-9")).thenReturn(null);
        ClientEntity clientFound = clientService.getClientByRut("12345672-9");
        assertThat(clientFound).isNull();
    }
    @Test
    public void updateClient() {
        ClientEntity client = new ClientEntity(3L, "12345678-9", "Juan", "Perez", 17, "", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findById(3L)).thenReturn(java.util.Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);
        ClientEntity updatedClient = clientService.updateClient(client);
        assertThat(updatedClient).isEqualTo(client);
    }
    @Test
    public void updateClientNotExist(){
        ClientEntity client = new ClientEntity(3L, "12345678-9", "Juan", "Perez", 17, "", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findById(3L)).thenReturn(java.util.Optional.of(client));
        ClientEntity updatedClient = clientService.updateClient(new ClientEntity (4L, "12345678-9", "Juan", "Perez", 17, "", "12345678", "Calle Falsa 123", "1234"));
        assertThat(updatedClient).isNull();

    }
    @Test
    public void deleteClient() throws Exception {
        ClientEntity client = new ClientEntity(3L, "12345678-9", "Juan", "Perez", 17, "", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findById(3L)).thenReturn(java.util.Optional.of(client));
        boolean deleted = clientService.deleteClient(3L);
        assertThat(deleted).isTrue();
    }
    @Test
    public void deleteClientWhenClientDoesNotExist() {
        doThrow(new RuntimeException("Client not found")).when(clientRepository).deleteById(1L);
        assertThrows(Exception.class, () -> clientService.deleteClient(1L));
    }
}
