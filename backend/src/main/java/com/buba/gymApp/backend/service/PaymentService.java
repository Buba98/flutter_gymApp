package com.buba.gymApp.backend.service;

import com.buba.gymApp.backend.dao.subscriptionDAO.SubscriptionDAO;
import com.buba.gymApp.backend.dao.userDAO.UserDAO;
import com.buba.gymApp.backend.dao.userSubscriptionDAO.UserSubscriptionDAO;
import com.buba.gymApp.backend.model.administrationComponents.Subscription;
import com.buba.gymApp.backend.model.administrationComponents.User;
import com.buba.gymApp.backend.model.administrationComponents.UserSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    private final UserDAO userDAO;
    private final SubscriptionDAO subscriptionDAO;
    private final UserSubscriptionDAO userSubscriptionDAO;

    @Autowired
    public PaymentService(@Qualifier("postgresUser") UserDAO userDAO, @Qualifier("postgresSubscription") SubscriptionDAO subscriptionDAO, @Qualifier("postgresUserSubscription") UserSubscriptionDAO userSubscriptionDAO) {
        this.userDAO = userDAO;
        this.subscriptionDAO = subscriptionDAO;
        this.userSubscriptionDAO = userSubscriptionDAO;
    }

    public boolean addInsurancePaymentByUserId(int id) {
        User user = userDAO.selectUserById(id);
        if (user == null)
            return false;
        user.getInsurances().add(new Date());
        return userDAO.updateUserById(user);
    }

    public boolean addSubscription(int userId, int subscriptionId) {
        User user = userDAO.selectUserById(userId);
        Subscription subscription = subscriptionDAO.selectSubscriptionById(subscriptionId);

        if (user == null || subscription == null)
            return false;



        UserSubscription userSubscription = new UserSubscription(null, subscription.getId(), 0, user.getId(), new Date(), new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(subscription.getMouthDuration() * 31)));

        userSubscription = userSubscriptionDAO.insertSubscription(userSubscription);

        if (userSubscription == null)
            return false;
        else
            return true;
    }

}
