package com.buba.gymApp.backend.dao.sessionDAO;

import com.buba.gymApp.backend.model.administrationComponents.Session;

import java.util.UUID;

public interface SessionDAO {

    Session insertSession(int userId);

    Session selectSessionByUserId(int userId);

    boolean deleteSessionByUserId(int userId);

    Session selectSessionByUUID(UUID id);

    boolean deleteSessionByUUID(UUID uuid);
}
