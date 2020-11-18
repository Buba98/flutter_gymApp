package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.treaningComponents.Set;

import java.time.Duration;

public interface SetDAO {
    Set insertSet(int[] reps, Duration rest, Duration eccentricDuration, Duration concentricDuration, Duration setDuration);

    Set selectSetById(int id);

    Set updateSet(Set set);
}
