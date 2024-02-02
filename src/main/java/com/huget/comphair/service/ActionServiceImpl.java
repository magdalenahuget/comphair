package com.huget.comphair.service;

import com.huget.comphair.exception.ResourceNotFoundException;
import com.huget.comphair.model.Action;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.repository.ActionRepository;
import com.huget.comphair.repository.HairdresserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;
    private HairdresserRepository hairdresserRepository;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository, HairdresserRepository hairdresserRepository) {
        this.actionRepository = actionRepository;
        this.hairdresserRepository = hairdresserRepository;
    }

    @Override
    public List<Action> getAllActions() {
        List<Action> actions = new ArrayList<Action>();

        actionRepository.findAll().forEach(actions::add);
        return actions;
    }

    @Override
    public List<Action> getAllActionsByHairdresserId(Long hairdresserId) {
        if(!hairdresserRepository.existsById(hairdresserId)) {
            throw new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId);
        }
        return actionRepository.findActionByHairdressersId(hairdresserId);
    }

    @Override
    public Action getActionById(Long id) {
        return actionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found action with id = " + id));
    }

    @Override
    public List<Hairdresser> getAllHairdressersByActionId(Long actionId) {
        if (!actionRepository.existsById(actionId)) {
            throw new ResourceNotFoundException("Not found action with id = " + actionId);
        }
        return hairdresserRepository.findHairdressersByActionsId(actionId);
    }

    @Override
    public Action addAction(Long hairdresserId, Action actionRequest) {
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
        return action;
    }

    @Override
    public Action updateAction(long id, Action actionRequest) {
        Action action = actionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("action id " + id + "not found"));

        action.setName(actionRequest.getName());
        return action;
    }

    @Override
    public void deleteActionFromHairdresser(Long hairdresserId, Long actionId) {
        Hairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId));
        hairdresser.removeAction(actionId);
        hairdresserRepository.save(hairdresser);
    }

    @Override
    public void deleteAction(Long id) {
        List<Hairdresser> hairdressers = hairdresserRepository.findHairdressersByActionsId(id);
        for (Hairdresser hairdresser : hairdressers) {
            hairdresser.removeAction(id);
        }
        hairdresserRepository.saveAll(hairdressers);
        actionRepository.deleteById(id);
    }
}
