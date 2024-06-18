package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    void deleteByTopicIdAndUserId(Long topicId, Long userId);

    List<Subscription> findByUserId(Long userId);
}
