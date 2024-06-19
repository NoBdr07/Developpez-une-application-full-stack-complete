package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByTopicIdIn(List<Long> topicIds);
}
