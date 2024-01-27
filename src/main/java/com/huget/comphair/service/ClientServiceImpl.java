package com.huget.comphair.service;

import com.huget.comphair.model.Client;
import com.huget.comphair.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClientsByHairdresserId(Long hairdresserId) {
        return null;
    }

    @Override
    public Client getClientById(Long clientId) {
        return null;
    }

    @Override
    public Client createClient(Long hairdresserId, Client clientRequest) {
        return null;
    }

    @Override
    public Client updateClient(Long clientId, Client clientRequest) {
        return null;
    }

    @Override
    public void deleteClient(Long clientId) {

    }

    @Override
    public void deleteAllClientsOfHairdresser(Long hairdresserId) {

    }

    @Override
    public List<Client> getAllClientsByHairdresserNick(String hairdresserNick) {
        return null;
    }

    @Override
    public List<Client> getClientsByLastName(String clientLastName) {
        return null;
    }
}
