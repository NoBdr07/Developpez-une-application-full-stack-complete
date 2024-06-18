package com.openclassrooms.mddapi.serviceImpls;

import com.openclassrooms.mddapi.models.entities.Subscription;
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

    public List<Long> getSubscriptions(Long userId) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        List<Long> topicIds = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            topicIds.add(subscription.getTopicId());
        }
        return topicIds;
    }


}
