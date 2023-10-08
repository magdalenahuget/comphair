package com.huget.comphair.controller;

import com.huget.comphair.exception.ResourceNotFoundException;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserType;
import com.huget.comphair.repository.HairdresserDetailsRepository;
import com.huget.comphair.repository.HairdresserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HairdresserController {

    @Autowired
    HairdresserRepository hairdresserRepository;

    @Autowired
    private HairdresserDetailsRepository detailsRepository;


    @GetMapping("/hairdressers")
    public ResponseEntity<List<Hairdresser>> getAllHairdressers(@RequestParam(required = false) HairdresserType hairdresserType) {
        List<Hairdresser> hairdressers = new ArrayList<Hairdresser>();

        if (hairdresserType == null) {
            hairdresserRepository.findAll().forEach(hairdressers::add);
        } else {
            hairdresserRepository.findByHairdresserType(hairdresserType).forEach(hairdressers::add);
        }
        if (hairdressers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hairdressers, HttpStatus.OK);
    }

    @GetMapping("/hairdressers/{id}")
    public ResponseEntity<Hairdresser> getHairdresserById(@PathVariable("id") long id) {
        Hairdresser hairdresser = hairdresserRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found hairdresser with id = " + id));
        return new ResponseEntity<>(hairdresser, HttpStatus.OK);
    }

    @PostMapping("/hairdressers")
    public ResponseEntity<Hairdresser> createHairdresser(@RequestBody Hairdresser hairdresser){
        Hairdresser hairdresser1 = hairdresserRepository.save(new Hairdresser(
                hairdresser.getName(),
                hairdresser.getHairdresserType(),
                hairdresser.getEmail(),
                hairdresser.getPassword()));
        return new ResponseEntity<>(hairdresser1, HttpStatus.CREATED);
    }

    @PutMapping("/hairdressers/{id}")
    public ResponseEntity<Hairdresser> updateHairdresser(@PathVariable("id") long id, @RequestBody Hairdresser hairdresser){
        Hairdresser hairdresser1 = hairdresserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Hairdresser with id = " + id));
        hairdresser1.setName(hairdresser.getName());
        hairdresser1.setHairdresserType(hairdresser.getHairdresserType());
        return new ResponseEntity<>(hairdresserRepository.save(hairdresser), HttpStatus.OK);
    }

    @DeleteMapping("/hairdressers/{id}")
    public ResponseEntity<HttpStatus> deleteHairdresser(@PathVariable("id") long id) {
        if(detailsRepository.existsById(id)){
            detailsRepository.deleteById(id);
        }
        hairdresserRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/hairdressers")
    public ResponseEntity<HttpStatus> deleteAllHairdressers(){
        hairdresserRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}