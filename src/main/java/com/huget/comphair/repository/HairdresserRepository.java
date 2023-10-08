package com.huget.comphair.repository;

import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HairdresserRepository extends JpaRepository<Hairdresser, Long> {

    List<Hairdresser> findByName(String name);

    List<Hairdresser> findByHairdresserType(HairdresserType hairdresserType);
}