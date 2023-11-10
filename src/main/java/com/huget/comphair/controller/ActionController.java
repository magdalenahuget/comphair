package com.huget.comphair.controller;

import com.huget.comphair.exception.ResourceNotFoundException;
import com.huget.comphair.model.Action;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.repository.ActionRepository;
import com.huget.comphair.repository.HairdresserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ActionController {

    @Autowired
    private HairdresserRepository hairdresserRepository;

    @Autowired
    private ActionRepository actionRepository;

    @GetMapping("/actions")
    public ResponseEntity<List<Action>> getAllAction(){
        List<Action> actions = new ArrayList<Action>();

        actionRepository.findAll().forEach(actions::add);

        if(actions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @GetMapping("/hairdressers/{hairdresserId}/actions")
    public ResponseEntity<List<Action>> getAllActionsByHairdresserId(@PathVariable(value = "hairdresserId") Long hairdresserId){
        if(!hairdresserRepository.existsById(hairdresserId)) {
            throw new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId);
        }
        List<Action> actions = actionRepository.findActionByHairdressersId(hairdresserId);
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @GetMapping("/actions/{id}")
    public ResponseEntity<Action> getActionsById(@PathVariable(value="id") Long id){
        Action action = actionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found action with id = " + id));

        return new ResponseEntity<>(action, HttpStatus.OK);
    }

    @GetMapping("/actions/{actionId}/hairdressers")
    public ResponseEntity<List<Hairdresser>> getAllHairdressersByActionsId(@PathVariable(value = "actionId") Long actionId) {
        if (!actionRepository.existsById(actionId)) {
            throw new ResourceNotFoundException("Not found action with id = " + actionId);
        }

        List<Hairdresser> hairdressers = hairdresserRepository.findHairdressersByActionsId(actionId);
        return new ResponseEntity<>(hairdressers, HttpStatus.OK);
    }

    @PostMapping("/hairdressers/{hairdresserId}/actions")
    public ResponseEntity<Action> addAction(@PathVariable(value = "hairdresserId") Long hairdresserId, @RequestBody Action actionRequest) {
        Action action = hairdresserRepository.findById(hairdresserId).map(hairdresser -> {
            long actionId = actionRequest.getId();

            if (actionId != 0L) {
                Action actionFromRequest = actionRepository.findById(actionId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found action with id = " + actionId));
                hairdresser.addAction(actionFromRequest);
                hairdresserRepository.save(hairdresser);
                return actionFromRequest;
            }

            hairdresser.addAction(actionRequest);
            return actionRepository.save(actionRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId));

        return new ResponseEntity<>(action, HttpStatus.CREATED);
    }

    @PutMapping("/actions/{id}")
    public ResponseEntity<Action> updateAction(@PathVariable("id") long id, @RequestBody Action actionRequest) {
        Action action = actionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("action id " + id + "not found"));

        action.setName(actionRequest.getName());

        return new ResponseEntity<>(actionRepository.save(action), HttpStatus.OK);
    }

    @DeleteMapping("/hairdressers/{hairdresserId}/actions/{actionId}")
    public ResponseEntity<HttpStatus> deleteActionFromHairdresser(@PathVariable(value = "hairdresserId")
                                                                      Long hairdresserId, @PathVariable(value = "actionId")
    Long actionId) {
        Hairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId));

        hairdresser.removeAction(actionId);
        hairdresserRepository.save(hairdresser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/actions/{id}")
    public ResponseEntity<HttpStatus> deleteAction(@PathVariable("id") long id) {
        List<Hairdresser> hairdressers = hairdresserRepository.findHairdressersByActionsId(id);
        for (Hairdresser hairdresser : hairdressers) {
            hairdresser.removeAction(id);
        }
        hairdresserRepository.saveAll(hairdressers);
        actionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
