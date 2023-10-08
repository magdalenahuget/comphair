package com.huget.comphair.repository;

import com.huget.comphair.model.HairdresserDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairdresserDetailsRepository extends JpaRepository<HairdresserDetails, Long> {

    @Transactional
    void deleteById(long id);

    @Transactional
    void deleteByHairdresserId(long hairdresserId);
}