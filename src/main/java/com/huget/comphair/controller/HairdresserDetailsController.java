package com.huget.comphair.controller;

import com.huget.comphair.exception.ResourceNotFoundException;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserDetails;
import com.huget.comphair.repository.HairdresserDetailsRepository;
import com.huget.comphair.repository.HairdresserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class HairdresserDetailsController {

    @Autowired
    private HairdresserDetailsRepository hairdresserDetailsRepository;

    @Autowired
    private HairdresserRepository hairdresserRepository;

    @GetMapping({"/details/{id}", "/hairdressers/{id}/details"})
    public ResponseEntity<HairdresserDetails> getDetailsById(@PathVariable(value = "id") Long id) {
        HairdresserDetails details = hairdresserDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Hairdresser Details with id = " + id));
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PostMapping("/hairdressers/{hairdresserId}/details")
    public ResponseEntity<HairdresserDetails> createDetails(
            @PathVariable(value = "hairdresserId")Long hairdresserId,
            @RequestBody HairdresserDetails hairdresserDetailsRequest) {
        Hairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Hairdresser with id = " + hairdresserId));
        hairdresserDetailsRequest.setFirstName(hairdresserDetailsRequest.getFirstName());
        hairdresserDetailsRequest.setLastName(hairdresserDetailsRequest.getLastName());
        hairdresserDetailsRequest.setDescription(hairdresserDetailsRequest.getDescription());
        hairdresserDetailsRequest.setHairdresser(hairdresser);
        HairdresserDetails details = hairdresserDetailsRepository.save(hairdresserDetailsRequest);
        return  new ResponseEntity<>(details, HttpStatus.CREATED);
    }

    @PutMapping("/details/{id}")
    public ResponseEntity<HairdresserDetails> updateDescription(@PathVariable("id") Long id,
                                                            @RequestBody HairdresserDetails hairdresserDetailsRequest){
        HairdresserDetails details = hairdresserDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));

        details.setDescription(hairdresserDetailsRequest.getDescription());
        return new ResponseEntity<>(hairdresserDetailsRepository.save(hairdresserDetailsRequest), HttpStatus.OK);
    }

    @DeleteMapping("/details/{id}")
    public ResponseEntity<HttpStatus> deleteDetails(@PathVariable("id") long id){
        hairdresserDetailsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/hairdressers/{hairdresserId}/details")
    public ResponseEntity<HairdresserDetails> deleteDetailsOfHairdresser(@PathVariable(value = "hairdresserId") Long hairdresserId){
        if(!hairdresserRepository.existsById(hairdresserId)){
            throw new ResourceNotFoundException("Not found Hairdresser with id = " + hairdresserId);
        }
        hairdresserDetailsRepository.deleteByHairdresserId(hairdresserId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
