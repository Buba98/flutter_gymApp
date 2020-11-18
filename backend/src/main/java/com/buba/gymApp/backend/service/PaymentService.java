package com.buba.gymApp.backend.service;

import com.buba.gymApp.backend.dao.interfaces.SubscriptionDAO;
import com.buba.gymApp.backend.dao.interfaces.UserDAO;
import com.buba.gymApp.backend.dao.interfaces.UserSubscriptionDAO;
import com.buba.gymApp.backend.model.administrationComponents.Subscription;
import com.buba.gymApp.backend.model.administrationComponents.User;
import com.buba.gymApp.backend.model.administrationComponents.UserSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    public int addInsurancePaymentByUserId(int id) {
        User user = userDAO.selectUserById(id);
        if (user == null)
            return 0;

        for (Date insurance : user.getInsurances()) {
            if (insurance.after(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(365))))
                return 2;
        }

        Date[] insurances = user.getInsurances();

        Date[] newInsurances = new Date[insurances.length + 1];

        insurances[0] = new Date();
        System.arraycopy(insurances, 0, newInsurances, 1, insurances.length);

        user.setInsurances(newInsurances);

        if (userDAO.updateUserById(user) == null)
            return -1;
        else
            return 1;
    }

    public int addUserSubscription(int userId, int subscriptionId) {
        User user = userDAO.selectUserById(userId);
        Subscription subscription = subscriptionDAO.selectSubscriptionById(subscriptionId);

        if (subscription == null) {
            return 0;
        } else if (user == null) {
            return 1;
        }

        if (userSubscriptionDAO.selectNotExpiredUserSubscriptionsByUserId(user.getId()) != null)
            return 3;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, subscription.getMouthDuration());

        UserSubscription userSubscription = userSubscriptionDAO.insertUserSubscription(subscriptionId, 0, userId, cal.getTime());

        if (userSubscription == null)
            return -1;
        else
            return 2;
    }

    public int addSubscription(int maxEntrance, float cost, int mouthDuration, String name){

        if (subscriptionDAO.selectSubscriptionByName(name) != null)
            return 0;

        if (subscriptionDAO.insertSubscription(mouthDuration, cost, maxEntrance, name) == null)
            return -1;
        else
            return 1;
    }

    public int addEntrance(int userId){
        User user = userDAO.selectUserById(userId);

        if (user == null)
            return 0;

        UserSubscription userSubscription = userSubscriptionDAO.selectNotExpiredUserSubscriptionsByUserId(user.getId());

        if (userSubscription == null){
            return 1;
        }

        Subscription subscription = subscriptionDAO.selectSubscriptionById(userSubscription.getSubscriptionId());

        if (subscription == null)
            return 2;

        if (userSubscription.getEntranceDone() < subscription.getMaxEntrances()) {
            userSubscription.setEntranceDone(userSubscription.getEntranceDone() + 1);
            userSubscriptionDAO.updateUserSubscription(userSubscription);
            return 0;
        }
        else
            return 3;
    }
}
