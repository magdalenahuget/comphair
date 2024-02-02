package com.huget.comphair.controller;

import com.huget.comphair.model.Action;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.repository.ActionRepository;
import com.huget.comphair.repository.HairdresserRepository;
import com.huget.comphair.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ActionController {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private ActionService actionService;

    @GetMapping("/actions")
    public ResponseEntity<List<Action>> getAllAction(){
        List<Action> actions = actionService.getAllActions();

        if(actions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @GetMapping("/hairdressers/{hairdresserId}/actions")
    public ResponseEntity<List<Action>> getAllActionsByHairdresserId(@PathVariable(value = "hairdresserId") Long hairdresserId){
        List<Action> actions = actionService.getAllActionsByHairdresserId(hairdresserId);
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @GetMapping("/actions/{id}")
    public ResponseEntity<Action> getActionsById(@PathVariable(value="id") Long id){
        Action action = actionService.getActionById(id);
        return new ResponseEntity<>(action, HttpStatus.OK);
    }

    @GetMapping("/actions/{actionId}/hairdressers")
    public ResponseEntity<List<Hairdresser>> getAllHairdressersByActionsId(@PathVariable(value = "actionId") Long actionId) {
        List<Hairdresser> hairdressers = actionService.getAllHairdressersByActionId(actionId);
        return new ResponseEntity<>(hairdressers, HttpStatus.OK);
    }

    @PostMapping("/hairdressers/{hairdresserId}/actions")
    public ResponseEntity<Action> addAction(@PathVariable(value = "hairdresserId") Long hairdresserId, @RequestBody Action actionRequest) {
        Action action = actionService.addAction(hairdresserId, actionRequest);
        return new ResponseEntity<>(action, HttpStatus.CREATED);
    }

    @PutMapping("/actions/{id}")
    public ResponseEntity<Action> updateAction(@PathVariable("id") long id, @RequestBody Action actionRequest) {
        Action action = actionService.updateAction(id, actionRequest);
        return new ResponseEntity<>(actionRepository.save(action), HttpStatus.OK);
    }

    @DeleteMapping("/hairdressers/{hairdresserId}/actions/{actionId}")
    public ResponseEntity<HttpStatus> deleteActionFromHairdresser(@PathVariable(value = "hairdresserId")
                                                                      Long hairdresserId, @PathVariable(value = "actionId")
    Long actionId) {
        actionService.deleteActionFromHairdresser(hairdresserId, actionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/actions/{id}")
    public ResponseEntity<HttpStatus> deleteAction(@PathVariable("id") long id) {
        actionService.deleteAction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
