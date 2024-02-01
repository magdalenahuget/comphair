package com.huget.comphair.controller;

import com.huget.comphair.model.HairdresserDetails;
import com.huget.comphair.repository.HairdresserDetailsRepository;
import com.huget.comphair.repository.HairdresserRepository;
import com.huget.comphair.service.HairdresserDetailsService;
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
    private HairdresserDetailsService hairdresserDetailsService;

    @Autowired
    private HairdresserRepository hairdresserRepository;

    @GetMapping({"/details/{id}", "/hairdressers/{id}/details"})
    public ResponseEntity<HairdresserDetails> getDetailsById(@PathVariable(value = "id") Long id) {
        HairdresserDetails details = hairdresserDetailsService.getDetailsById(id);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PostMapping("/hairdressers/{hairdresserId}/details")
    // TODO: handle rejecting request when hairdresser already has details
    public ResponseEntity<HairdresserDetails> createDetails(
            @PathVariable(value = "hairdresserId")Long hairdresserId,
            @RequestBody HairdresserDetails hairdresserDetailsRequest) {
        HairdresserDetails details = hairdresserDetailsService.createDetails(hairdresserId, hairdresserDetailsRequest);
        return  new ResponseEntity<>(details, HttpStatus.CREATED);
    }

    @PutMapping("/details/{id}")
    public ResponseEntity<HairdresserDetails> updateDescription(@PathVariable("id") Long id,
                                                            @RequestBody HairdresserDetails hairdresserDetailsRequest){
        HairdresserDetails hairdresserDetails = hairdresserDetailsService.updateDescription(id, hairdresserDetailsRequest);
        return new ResponseEntity<>(hairdresserDetailsRepository.save(hairdresserDetails), HttpStatus.OK);
    }

    @DeleteMapping("/details/{id}")
    public ResponseEntity<HttpStatus> deleteDetailsById(@PathVariable("id") long id){
        hairdresserDetailsService.deleteDetailsById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/hairdressers/{hairdresserId}/details")
    public ResponseEntity<HairdresserDetails> deleteDetailsOfHairdresser(@PathVariable(value = "hairdresserId") Long hairdresserId){
        hairdresserDetailsService.deleteDetailsOfHairdresser(hairdresserId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
