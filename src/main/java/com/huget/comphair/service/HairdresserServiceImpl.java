package com.huget.comphair.service;

import com.huget.comphair.exception.ResourceNotFoundException;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserType;
import com.huget.comphair.repository.HairdresserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class HairdresserServiceImpl implements HairdresserService{

    HairdresserRepository hairdresserRepository;

    @Autowired
    public HairdresserServiceImpl(HairdresserRepository hairdresserRepository) {
        this.hairdresserRepository = hairdresserRepository;
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
        Hairdresser hairdresser =  hairdresserRepository.findById(hairdresserId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId));
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
    public void deleteHairdresser() {

    }

    @Override
    public void deleteAllHairdressers() {

    }
}
