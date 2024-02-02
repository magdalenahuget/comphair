package com.huget.comphair.service;

import com.huget.comphair.model.Action;
import com.huget.comphair.model.Hairdresser;

import java.util.List;

public class ActionServiceImpl implements ActionService {
    @Override
    public List<Action> getAllActions() {
        return null;
    }

    @Override
    public List<Action> getAllActionsByHairdresserId(Long hairdresserId) {
        return null;
    }

    @Override
    public Action getActionById(Long id) {
        return null;
    }

    @Override
    public List<Hairdresser> getAllHairdressersByActionId(Long actionId) {
        return null;
    }

    @Override
    public Action addAction(Long hairdresserId, Action action) {
        return null;
    }

    @Override
    public Action updateAction(long id, Action action) {
        return null;
    }

    @Override
    public void deleteActionFromHairdresser(Long hairdresserId, Long actionId) {

    }

    @Override
    public void deleteAction(Long id) {

    }
}
