package com.openclassrooms.mddapi.serviceImpls;

import com.openclassrooms.mddapi.mappers.TopicMapper;
import com.openclassrooms.mddapi.models.entities.Subscription;
import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.repositories.SubscriptionRepository;
import com.openclassrooms.mddapi.repositories.TopicRepository;
import com.openclassrooms.mddapi.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(Long topicId) {
        return topicRepository.findById(topicId);
    }

    public List<Topic> getTopicsByIds(List<Long> topicIds) {
        return topicRepository.findByTopicIdIn(topicIds);
    }

    public void subscribe(Long topicId, Long userId) {
        Subscription sub = new Subscription();
        sub.setTopicId(topicId);
        sub.setUserId(userId);
        subscriptionRepository.save(sub);
    }

    @Transactional
    public void unsubscribe(Long topicId, Long userId) {
        subscriptionRepository.deleteByTopicIdAndUserId(topicId, userId);
    }
}
