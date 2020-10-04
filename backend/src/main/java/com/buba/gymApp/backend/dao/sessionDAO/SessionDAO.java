package com.buba.gymApp.backend.dao.sessionDAO;

import java.util.UUID;

public interface SessionDAO {

    UUID insertSession(int userId);

    UUID selectSessionByUserId(int userId);

    void deleteSessionByUserId(int userId);

    int selectSessionByUUID(UUID id);
}
