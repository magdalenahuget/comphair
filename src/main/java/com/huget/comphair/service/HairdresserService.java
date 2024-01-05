package com.huget.comphair.service;

import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserType;

import java.util.List;

public interface HairdresserService {

    List<Hairdresser> getAllHairdressers(HairdresserType hairdresserType);

    Hairdresser getHairdresserById(long hairdresserId);

    Hairdresser createHairdresser();

    Hairdresser updateHairdresser();

    void deleteHairdresser(long hairdresserId);

    void deleteAllHairdressers();
}
