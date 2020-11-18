package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.administrationComponents.Subscription;

public interface SubscriptionDAO {
    boolean deleteSubscriptionById(int id);

    Subscription selectSubscriptionByEverything(Subscription subscription);

    Subscription insertSubscription(Subscription subscription);

    Subscription selectSubscriptionById(int id);
}
