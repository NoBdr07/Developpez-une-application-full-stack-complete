package com.openclassrooms.mddapi.services;

import java.util.List;

public interface SubscriptionService {

    List<Long> getSubscriptions(Long userId);
}
