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

        user.getInsurances().add(new Date());

        if (userDAO.updateUserById(user))
            return 1;
        else
            return -1;
    }

    public int addUserSubscription(int userId, int subscriptionId) {
        User user = userDAO.selectUserById(userId);
        Subscription subscription = subscriptionDAO.selectSubscriptionById(subscriptionId);

        if (subscription == null) {
            return 0;
        } else if (user == null) {
            return 1;
        }

        if (userSubscriptionDAO.selectAllNotExpiredUserSubscriptionsByUserId(user.getId()).size() != 0)
            return 3;

        UserSubscription userSubscription = new UserSubscription(null, subscription.getId(), 0, user.getId(), new Date(), new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(subscription.getMouthDuration() * 31)));

        userSubscription = userSubscriptionDAO.insertUserSubscription(userSubscription);

        if (userSubscription == null)
            return -1;
        else
            return 2;
    }

    public int addSubscription(int maxEntrance, float cost, int mouthDuration){
        Subscription subscription = new Subscription(null, mouthDuration, cost, maxEntrance);

        if (subscriptionDAO.selectSubscriptionByEverything(subscription) != null)
            return 0;

        if (subscriptionDAO.insertSubscription(subscription) == null)
            return -1;
        else
            return 1;
    }

    public int addEntrance(int userId){
        User user = userDAO.selectUserById(userId);

        if (user == null)
            return 0;

        List<UserSubscription> userSubscriptionList = userSubscriptionDAO.selectAllNotExpiredUserSubscriptionsByUserId(user.getId());

        if (userSubscriptionList == null || userSubscriptionList.isEmpty()){
            return 1;
        }

        for (UserSubscription userSubscription : userSubscriptionList){
            if (subscriptionDAO.selectSubscriptionById(userSubscription.getSubscriptionId()).getMaxEntrances() > userSubscription.getEntranceDone()){
                userSubscription.setEntranceDone(userSubscription.getEntranceDone() + 1);
                userSubscriptionDAO.updateUserSubscription(userSubscription);
                return 0;
            }
        }
        return 1; //todo
    }
}
