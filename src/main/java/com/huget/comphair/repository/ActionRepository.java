package com.huget.comphair.repository;

import com.huget.comphair.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    List<Action> findActionByHairdressersId(Long hairdresserId);
}