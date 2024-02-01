package com.huget.comphair.controller;

import com.huget.comphair.model.Client;
import com.huget.comphair.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping({"/hairdressers/{hairdresserId}/clients"})
    public ResponseEntity<List<Client>> getAllClientsByHairdresserId(
            @PathVariable(value = "hairdresserId") Long hairdresserId) {
        List<Client> clients = clientService.getAllClientsByHairdresserId(hairdresserId);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Client> getClientById(
            @PathVariable(value = "clientId") Long clientId) {
        Client client = clientService.getClientById(clientId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/hairdressers/{hairdresserId}/clients")
    public ResponseEntity<Client> createClient(@PathVariable(value = "hairdresserId") Long hairdresserId,
                                               @RequestBody Client clientRequest) {
        Client client = clientService.createClient(hairdresserId, clientRequest);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping("/clients/update/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable("clientId") Long clientId,
                                               @RequestBody Client clientRequest) {
        Client newClient = clientService.updateClient(clientId, clientRequest);
        return new ResponseEntity<>(newClient, HttpStatus.OK);
    }

    @DeleteMapping("/clients/delete/{clientId}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("clientId") long clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/hairdressers/{hairdresserId}/clients")
    public ResponseEntity<HttpStatus> deleteAllClientsOfHairdresser(@PathVariable(value = "hairdresserId") Long hairdresserId) {
        clientService.deleteAllClientsOfHairdresser(hairdresserId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping({"/hairdressers/{hairdresserNick}/clients"})
    public ResponseEntity<List<Client>> getAllClientsByHairdresserNick(
            @PathVariable(value = "hairdresserNick") String hairdresserNick) {
        List<Client> clients = clientService.getAllClientsByHairdresserNick(hairdresserNick);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/clients/{clientLastName}")
    public ResponseEntity<List<Client>> getClientsByLastName(
            @PathVariable(value = "clientLastName") String clientLastName) {
        List<Client> clients = clientService.getClientsByLastName(clientLastName);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
