package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.entities.Subscription;
import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    void deleteByTopicAndUser(Topic topic, User user);

    List<Subscription> findByUserId(Long userId);
}
