package com.openclassrooms.mddapi.serviceImpls;

import com.openclassrooms.mddapi.models.entities.Subscription;
import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.repositories.SubscriptionRepository;
import com.openclassrooms.mddapi.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    /**
     * Get all subscriptions for a user
     * @param user The user id
     * @return A list of topic ids
     */
    public List<Topic> getSubscriptions(User user) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(user.getId());
        List<Topic> topics = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            topics.add(subscription.getTopic());
        }
        return topics;
    }
}
