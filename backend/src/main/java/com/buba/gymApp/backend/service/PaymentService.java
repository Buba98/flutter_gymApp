package com.buba.gymApp.backend.service;

import com.buba.gymApp.backend.dao.sessionDAO.SessionDAO;
import com.buba.gymApp.backend.dao.userDAO.UserDAO;
import com.buba.gymApp.backend.model.administrationComponents.Subscription;
import com.buba.gymApp.backend.model.administrationComponents.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {

    private final UserDAO userDAO;
    private final SessionDAO sessionDAO;

    @Autowired
    public PaymentService(@Qualifier("postgresUser") UserDAO userDAO, @Qualifier("postgresSession") SessionDAO sessionDAO){
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
    }

    public boolean addInsurancePaymentByUserId(int id){
        User user = userDAO.selectUserById(id);
        if (user == null)
            return false;
        user.getInsurances().add(new Date());
        return userDAO.updateUserById(user);
    }

    public boolean addSubscription(int userId, int subscriptionId){
        User user = userDAO.selectUserById(userId);
        Subscription subscription = null;

        return true;
    }

}
