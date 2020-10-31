package com.buba.gymApp.backend.service;

import com.buba.gymApp.backend.dao.userDAO.UserDAO;
import com.buba.gymApp.backend.dao.sessionDAO.SessionDAO;
import com.buba.gymApp.backend.model.administrationComponents.Session;
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
    public AccessService(@Qualifier("postgresUser") UserDAO userDAO, @Qualifier("postgresSession") SessionDAO sessionDAO){
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
    }

    public Session getSessionForSignIn(String email, String password){
        User user = userDAO.selectUserByEmail(email);

        if (user == null)
            return null;
        else if (!user.getPassword().equals(password))
            return null;
        else
            return sessionDAO.insertSession(user.getId());
    }

    public int signUp(String fiscalCode, String name, String surname, Date birthday, String email, String password, String phoneNumber, boolean owner){
        User user = new User(null, name, surname, fiscalCode, birthday, null, email, password, phoneNumber, null, null, owner);

        if (userDAO.selectUserByEmail(email) != null)
            return 1;
        if (userDAO.selectUserByFiscalCode(fiscalCode) != null)
            return 0;

        if (userDAO.insertUser(user).getId() == null)
            return -1;
        else return 2;
    }

    public boolean isOwner(UUID id){
        return userDAO.selectUserById(sessionDAO.selectSessionByUUID(id).getUserId()).isOwner();
    }
}
