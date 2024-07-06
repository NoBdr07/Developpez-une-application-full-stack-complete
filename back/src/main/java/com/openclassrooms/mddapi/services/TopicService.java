package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface TopicService {

    List<Topic> getTopics();

    Optional<Topic> getTopicById(Long topicId);

    List<Topic> getTopicsByIds(List<Long> topicIds);

    void subscribe(Topic topic, User user);

    void unsubscribe(Topic topic, User user);


}
