package com.huget.comphair.service;

import com.huget.comphair.exception.ResourceNotFoundException;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserDetails;
import com.huget.comphair.repository.HairdresserDetailsRepository;
import com.huget.comphair.repository.HairdresserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HairdresserDetailsServiceImpl implements HairdresserDetailsService{

    private HairdresserDetailsRepository hairdresserDetailsRepository;
    private HairdresserRepository hairdresserRepository;

    public HairdresserDetailsServiceImpl(HairdresserDetailsRepository hairdresserDetailsRepository, HairdresserRepository hairdresserRepository) {
        this.hairdresserDetailsRepository = hairdresserDetailsRepository;
        this.hairdresserRepository = hairdresserRepository;
    }

    @Override
    public HairdresserDetails getDetailsById(Long id) {
        HairdresserDetails details = hairdresserDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Hairdresser Details with id = " + id));
        return details;
    }

    @Override
    public HairdresserDetails createDetails(Long hairdresserId, HairdresserDetails hairdresserDetails) {
        Hairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Hairdresser with id = " + hairdresserId));
        hairdresserDetails.setFirstName(hairdresserDetails.getFirstName());
        hairdresserDetails.setLastName(hairdresserDetails.getLastName());
        hairdresserDetails.setDescription(hairdresserDetails.getDescription());
        hairdresserDetails.setHairdresser(hairdresser);
        HairdresserDetails details = hairdresserDetailsRepository.save(hairdresserDetails);
        return details;
    }

    @Override
    public HairdresserDetails updateDescription(Long id, HairdresserDetails hairdresserDetailsRequest) {
        HairdresserDetails hairdresserDetails = hairdresserDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));
        hairdresserDetails.setDescription(hairdresserDetailsRequest.getDescription());
        return null;
    }

    @Override
    public void deleteDetailsById(Long id) {
        hairdresserDetailsRepository.deleteById(id);
    }

    @Override
    public void deleteDetailsOfHairdresser(Long hairdresserId) {
        if(!hairdresserRepository.existsById(hairdresserId)){
            throw new ResourceNotFoundException("Not found Hairdresser with id = " + hairdresserId);
        }
        hairdresserDetailsRepository.deleteByHairdresserId(hairdresserId);
    }
}
