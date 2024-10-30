package services;
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

import java.util.List;
import java.util.Optional;

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
    public void testGetClients() {
        List<ClientEntity> clients = clientService.getClients();
        verify(clientRepository).findAll();
    }

    @Test
    public void dontSaveClientIfRutExists() {
        ClientEntity client = new ClientEntity();
        client.setRut("12345678-9");
        clientService.saveClient(client);
        ClientEntity client2 = new ClientEntity();
        client2.setRut("12345678-9");
        clientService.saveClient(client2);
        assertNull(clientService.saveClient(client2));
    }
}
