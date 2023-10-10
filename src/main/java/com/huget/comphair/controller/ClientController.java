package com.huget.comphair.controller;

import com.huget.comphair.exception.ResourceNotFoundException;
import com.huget.comphair.model.Client;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.repository.ClientRepository;
import com.huget.comphair.repository.HairdresserRepository;
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
    private HairdresserRepository hairdresserRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping({"/hairdressers/{hairdresserId}/clients"})
    public ResponseEntity<List<Client>> getAllClientsByHairdresserId(
            @PathVariable(value = "hairdresserId") Long hairdresserId) {
        if (!hairdresserRepository.existsById(hairdresserId)) {
            throw new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId);
        }
        List<Client> clients = clientRepository.findByHairdresserId(hairdresserId);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(
            @PathVariable(value = "clientId") Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found client with id = " + clientId));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/hairdressers/{hairdresserId}/clients")
    public ResponseEntity<Client> createClient(@PathVariable(value = "hairdresserId") Long hairdresserId,
                                               @RequestBody Client clientRequest) {
        Hairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + hairdresserId));
        clientRequest.setHairdresser(hairdresser);
        Client client = clientRepository.save(clientRequest);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("clientId") Long clientId,
                                               @RequestBody Client clientRequest) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("ClientId " + clientId + " not found"));
        client.setNote(clientRequest.getNote());
        Client newClient = clientRepository.save(client);
        return new ResponseEntity<>(newClient, HttpStatus.OK);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("clientId") long clientId) {
        clientRepository.deleteById(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/hairdressers/{hairdresserId}/clients")
    public ResponseEntity<Client> deleteAllClientsOfHairdresser(@PathVariable(value = "hairdresserId") Long hairdresserId) {
        if(hairdresserRepository.existsById(hairdresserId)){
            throw new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId);
        }
        clientRepository.deleteAllClientsOfHairdresser(hairdresserId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping({"/hairdressers/{hairdresserName}/clients"})
    public ResponseEntity<List<Client>> getAllClientsByHairdresserName(
            @PathVariable(value = "hairdresserName") String hairdresserName) {
        Hairdresser hairdresser = hairdresserRepository.findByName(hairdresserName);
        if (hairdresser == null) {
            throw new ResourceNotFoundException("Not found hairdresser with name = " + hairdresserName);
        }
        List<Client> clients = clientRepository.findAllClientsByHairdresserName(hairdresserName);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/clients/{clientLastName}")
    public ResponseEntity<List<Client>> getClientsByLastName(
            @PathVariable(value = "clientLastName") String clientLastName) {
        List<Client> clients = clientRepository.findClientsByLastName(clientLastName);

       if(clients.isEmpty()){
           throw new ResourceNotFoundException("No clients found with last name: " + clientLastName);
       }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
