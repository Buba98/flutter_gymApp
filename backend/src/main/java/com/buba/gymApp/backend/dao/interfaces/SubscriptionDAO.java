package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.administrationComponents.Subscription;

public interface SubscriptionDAO {
    boolean deleteSubscriptionById(int id);

    Subscription selectSubscriptionByName(String name);

    Subscription insertSubscription(int mouthDuration, float cost, int maxEntrances, String name);

    Subscription selectSubscriptionById(int id);
}
