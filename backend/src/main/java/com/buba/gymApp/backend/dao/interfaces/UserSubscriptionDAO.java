package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.administrationComponents.UserSubscription;

import java.util.List;

public interface UserSubscriptionDAO {
    UserSubscription insertUserSubscription(UserSubscription userSubscription);

    List<UserSubscription> getAllNotExpiredUserSubscriptionsByUserId(int userId);

    List<UserSubscription> getAllUserSubscriptionsByUserId(int userId);

    List<Integer> getAllUserSubscriptionsIdsByUserId(int userId);

    boolean updateAll(UserSubscription userSubscription);
}
