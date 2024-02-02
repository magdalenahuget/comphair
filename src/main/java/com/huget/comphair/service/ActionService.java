package com.huget.comphair.service;

import com.huget.comphair.model.Action;
import com.huget.comphair.model.Hairdresser;
import java.util.List;

public interface ActionService {

    List<Action> getAllActions();

    List<Action> getAllActionsByHairdresserId(Long hairdresserId);

    Action getActionById(Long id);

    List<Hairdresser> getAllHairdressersByActionId(Long actionId);

    Action addAction(Long hairdresserId, Action action);

    Action updateAction(long id, Action action);

    void deleteActionFromHairdresser(Long hairdresserId, Long actionId);

    void deleteAction(Long id);
}
