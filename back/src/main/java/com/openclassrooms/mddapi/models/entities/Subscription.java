package com.openclassrooms.mddapi.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long subscriptionId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "topic_id")
    private Long topicId;

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}
