package com.example.backendBanco.controllers;

import com.example.backendBanco.entities.ClientEntity;
import com.example.backendBanco.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/clients")
@CrossOrigin("*")

public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<ClientEntity>> listClients() {
        List<ClientEntity> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable Long id) {
        ClientEntity client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/")
    public ResponseEntity<ClientEntity> saveClient(@RequestBody ClientEntity client) {
        ClientEntity clientNew = clientService.saveClient(client);
        return ResponseEntity.ok(clientNew);
    }

    @PutMapping("/")
    public ResponseEntity<ClientEntity> updateClient(@RequestBody ClientEntity client){
        ClientEntity clientUpdated = clientService.updateClient(client);
        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteClientById(@PathVariable Long id) throws Exception {
        var isDeleted = clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginClient(@RequestBody ClientEntity client){
        boolean isLogged = clientService.loginClient(client.getEmail(), client.getPassword());
        if (isLogged) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
