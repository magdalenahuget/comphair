package com.huget.comphair.controller;

import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserType;
import com.huget.comphair.repository.HairdresserRepository;
import com.huget.comphair.service.HairdresserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HairdresserController {

    @Autowired
    HairdresserRepository hairdresserRepository;
    HairdresserService hairdresserService;

    @GetMapping("/hairdressers")
    public ResponseEntity<List<Hairdresser>> getAllHairdressers(@RequestParam(required = false) HairdresserType hairdresserType) {
        List<Hairdresser> hairdressers = hairdresserService.getAllHairdressers(hairdresserType);

        if (hairdressers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hairdressers, HttpStatus.OK);
    }

    @GetMapping("/hairdressers/{id}")
    public ResponseEntity<Hairdresser> getHairdresserById(@PathVariable("id") long id) {
        Hairdresser hairdresser = hairdresserService.getHairdresserById(id);
        return new ResponseEntity<>(hairdresser, HttpStatus.OK);
    }

    @GetMapping("/hairdressers/{email}")
    public ResponseEntity<Hairdresser> getHairdresserByEmail(@PathVariable("email") String email) {
        Hairdresser hairdresser = hairdresserService.getHairdresserByEmail(email);
        return new ResponseEntity<>(hairdresser, HttpStatus.OK);
    }

    @PostMapping("/hairdressers")
    public ResponseEntity<Hairdresser> createHairdresser(@RequestBody Hairdresser hairdresser){
        Hairdresser hairdresserCreated = hairdresserService.createHairdresser(hairdresser);
        return new ResponseEntity<>(hairdresserCreated, HttpStatus.CREATED);
    }

    @PutMapping("/hairdressers/{id}")
    public ResponseEntity<Hairdresser> updateHairdresser(@PathVariable("id") long id, @RequestBody Hairdresser hairdresser){
        Hairdresser hairdresserUpdated = hairdresserService.updateHairdresser(id, hairdresser);
        return new ResponseEntity<>(hairdresserUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/hairdressers/{id}")
    public ResponseEntity<HttpStatus> deleteHairdresser(@PathVariable("id") long id) {
        hairdresserService.deleteHairdresser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/hairdressers")
    public ResponseEntity<HttpStatus> deleteAllHairdressers(){
        hairdresserService.deleteAllHairdressers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}