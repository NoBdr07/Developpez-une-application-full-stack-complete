package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.entities.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {

    List<Topic> getTopics();

    Optional<Topic> getTopicById(Long topicId);

    void subscribe(Long topicId, Long userId);

    void unsubscribe(Long topicId, Long userId);
}
