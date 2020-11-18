package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.administrationComponents.UserSubscription;

import java.util.Date;
import java.util.List;

public interface UserSubscriptionDAO {
    UserSubscription insertUserSubscription(int subscriptionId, int entranceDone, int userId,
                                            Date endDate);

    UserSubscription selectNotExpiredUserSubscriptionsByUserId(int userId);

    List<UserSubscription> selectAllUserSubscriptionsByUserId(int userId);

    UserSubscription updateUserSubscription(UserSubscription userSubscription);
}
