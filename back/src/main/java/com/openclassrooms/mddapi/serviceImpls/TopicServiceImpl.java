package com.openclassrooms.mddapi.serviceImpls;

import com.openclassrooms.mddapi.models.entities.Subscription;
import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.models.entities.User;
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
    private SubscriptionRepository subscriptionRepository;

    /**
     * Get all topics
     * @return List of topics
     */
    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    /**
     * Get a topic by its ID
     * @param topicId The ID of the topic
     * @return The topic
     */
    public Optional<Topic> getTopicById(Long topicId) {
        return topicRepository.findById(topicId);
    }

    /**
     * Get topics by their IDs
     * @param topicIds A list of topic IDs
     * @return List of topics
     */
    public List<Topic> getTopicsByIds(List<Long> topicIds) {
        return topicRepository.findByTopicIdIn(topicIds);
    }

    /**
     * Subscribe to a topic
     * @param topic The topic
     * @param user The  user
     */
    public void subscribe(Topic topic, User user) {
        Subscription sub = new Subscription();
        sub.setTopic(topic);
        sub.setUser(user);
        subscriptionRepository.save(sub);
    }

    /**
     * Unsubscribe from a topic
     * Is transactional to ensure data consistency if the delete fails
     * @param topic The topic
     * @param user The  user
     */
    @Transactional
    public void unsubscribe(Topic topic, User user) {
        subscriptionRepository.deleteByTopicAndUser(topic, user);
    }
}
