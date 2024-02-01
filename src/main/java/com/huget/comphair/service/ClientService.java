package com.huget.comphair.service;

import com.huget.comphair.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClientsByHairdresserId(Long hairdresserId);

    Client getClientById(Long clientId);

    Client createClient(Long hairdresserId, Client clientRequest);

    Client updateClient(Long clientId, Client clientRequest);

    void deleteClient(Long clientId);

    void deleteAllClientsOfHairdresser(Long hairdresserId);

    List<Client> getAllClientsByHairdresserNick(String hairdresserNick);

    List<Client> getClientsByLastName(String clientLastName);

}
