package com.huget.comphair.service;

import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserType;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface HairdresserService {

    List<Hairdresser> getAllHairdressers(HairdresserType hairdresserType);

    Hairdresser getHairdresserById(long hairdresserId);

    Hairdresser getHairdresserByEmail(String email);

    Hairdresser createHairdresser(Hairdresser hairdresser);

    Hairdresser updateHairdresser(long id, @RequestBody Hairdresser hairdresser);

    void deleteHairdresser(long hairdresserId);

    void deleteAllHairdressers();
}
