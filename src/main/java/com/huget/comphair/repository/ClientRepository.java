package com.huget.comphair.repository;

import com.huget.comphair.model.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByHairdresserId(long hairdresserId);

    @Transactional
    void deleteByHairdresserId(long hairdresserId);

    List<Client> findClientsByLastName(String lastName);

    List<Client> findAllClientsByHairdresserNick(String hairdresserName);
}