package com.huget.comphair.service;

import com.huget.comphair.exception.ResourceNotFoundException;
import com.huget.comphair.model.Client;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.repository.ClientRepository;
import com.huget.comphair.repository.HairdresserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{

    private ClientRepository clientRepository;
    private HairdresserRepository hairdresserRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, HairdresserRepository hairdresserRepository) {
        this.clientRepository = clientRepository;
        this.hairdresserRepository = hairdresserRepository;
    }

    @Override
    public List<Client> getAllClientsByHairdresserId(Long hairdresserId) {
        if (!hairdresserRepository.existsById(hairdresserId)) {
            throw new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId);
        }
        return clientRepository.findByHairdresserId(hairdresserId);
    }

    @Override
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found client with id = " + clientId));
    }

    @Override
    public Client createClient(Long hairdresserId, Client clientRequest) {
        Hairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId));
        clientRequest.setHairdresser(hairdresser);
        return clientRepository.save(clientRequest);
    }

    @Override
    public Client updateClient(Long clientId, Client clientRequest) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("ClientId " + clientId + " not found"));
        client.setNote(clientRequest.getNote());
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public void deleteAllClientsOfHairdresser(Long hairdresserId) {
        if(hairdresserRepository.existsById(hairdresserId)){
            throw new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId);
        }
        clientRepository.deleteByHairdresserId(hairdresserId);
    }

    @Override
    public List<Client> getAllClientsByHairdresserNick(String hairdresserNick) {
        Hairdresser hairdresser = hairdresserRepository.findByNick(hairdresserNick);
        if (hairdresser == null) {
            throw new ResourceNotFoundException("Not found hairdresser with nick = " + hairdresserNick);
        }
        return clientRepository.findAllClientsByHairdresserNick(hairdresserNick);
    }

    @Override
    public List<Client> getClientsByLastName(String clientLastName) {
        List<Client> clients = clientRepository.findClientsByLastName(clientLastName);

        if(clients.isEmpty()){
            throw new ResourceNotFoundException("No clients found with last name: " + clientLastName);
        }
        return clients;
    }
}
