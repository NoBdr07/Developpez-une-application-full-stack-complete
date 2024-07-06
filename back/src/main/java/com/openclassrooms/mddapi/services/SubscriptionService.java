package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.models.entities.User;

import java.util.List;

public interface SubscriptionService {

    List<Topic> getSubscriptions(User user);
}
