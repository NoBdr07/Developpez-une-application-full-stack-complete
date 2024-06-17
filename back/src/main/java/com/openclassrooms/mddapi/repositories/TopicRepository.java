package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
