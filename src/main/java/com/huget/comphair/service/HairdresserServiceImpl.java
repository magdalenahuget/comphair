package com.huget.comphair.service;

import com.huget.comphair.exception.ResourceNotFoundException;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserType;
import com.huget.comphair.repository.HairdresserDetailsRepository;
import com.huget.comphair.repository.HairdresserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class HairdresserServiceImpl implements HairdresserService {

    HairdresserRepository hairdresserRepository;
    HairdresserDetailsRepository hairdresserDetailsRepository;

    @Autowired
    public HairdresserServiceImpl(HairdresserRepository hairdresserRepository, HairdresserDetailsRepository hairdresserDetailsRepository) {
        this.hairdresserRepository = hairdresserRepository;
        this.hairdresserDetailsRepository = hairdresserDetailsRepository;
    }

    @Override
    public List<Hairdresser> getAllHairdressers(HairdresserType hairdresserType) {
        log.info("Fetching all hairdressers.");
        List<Hairdresser> hairdressers = new ArrayList<Hairdresser>();
        if (hairdresserType == null) {
            hairdresserRepository.findAll().forEach(hairdressers::add);
        } else {
            hairdresserRepository.findByHairdresserType(hairdresserType).forEach(hairdressers::add);
        }
        log.info("Getting all hairdressers (count): {}", hairdressers.size());
        return hairdressers;
    }

    @Override
    public Hairdresser getHairdresserById(long hairdresserId) {
        log.info("Getting hairdresser by id: {}", hairdresserId);
        Hairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId));
        return hairdresser;
    }

    @Override
    public Hairdresser createHairdresser() {
        return null;
    }

    @Override
    public Hairdresser updateHairdresser() {
        return null;
    }

    @Override
    public void deleteHairdresser(long hairdresserId) {
        log.info("Hairdresser with id:{}", hairdresserId);
        if (hairdresserDetailsRepository.existsById(hairdresserId)) {
            hairdresserDetailsRepository.deleteById(hairdresserId);
        }
        hairdresserRepository.deleteById(hairdresserId);
    }

    @Override
    public void deleteAllHairdressers() {

    }
}
