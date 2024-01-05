package com.huget.comphair.service;

import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserType;

import java.util.List;

public interface HairdresserService {

    List<Hairdresser> getAllHairdressers(HairdresserType hairdresserType);

    Hairdresser getHairdresser();

    Hairdresser createHairdresser();

    Hairdresser updateHairdresser();

    void deleteHairdresser();

    void deleteAllHairdressers();
}
