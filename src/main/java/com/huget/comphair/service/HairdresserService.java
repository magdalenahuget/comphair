package com.huget.comphair.service;

import com.huget.comphair.model.Hairdresser;

import java.util.List;

public interface HairdresserService {

    List<Hairdresser> getAllHairdressers();

    Hairdresser getHairdresser();

    Hairdresser createHairdresser();

    Hairdresser updateHairdresser();

    void deleteHairdresser();

    void deleteAllHairdressers();
}
