package com.huget.comphair.service;

import com.huget.comphair.model.HairdresserDetails;

public interface HairdresserDetailsService {

    HairdresserDetails getDetailsById(Long id);

    HairdresserDetails createDetails(Long hairdresserId, HairdresserDetails hairdresserDetails);

    HairdresserDetails updateDescription(Long id, HairdresserDetails hairdresserDetails);

    void deleteDetailsById(Long id);

    void deleteDetailsOfHairdresser(Long hairdresserId);
}
