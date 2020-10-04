package com.buba.gymApp.backend.service;

import com.buba.gymApp.backend.dao.UserDAO;
import com.buba.gymApp.backend.dao.sessionDAO.SessionDAO;
import com.buba.gymApp.backend.model.administrationComponents.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AccessService {

    private final UserDAO userDAO;
    private final SessionDAO sessionDAO;

    @Autowired
    public AccessService(@Qualifier("postgres") UserDAO userDAO, @Qualifier("postgres") SessionDAO sessionDAO){
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
    }

    public UUID getUUIDForSignIn(String email, String password){
        User user = userDAO.selectUserByEmail(email);

        if (user == null)
            return null;

        return sessionDAO.insertSession(user.getId());
    }

    public int addUser(String fiscalCode, String name, String surname, Date birthday, String email, String password, String phoneNumber){
        return 0;
    }
}
