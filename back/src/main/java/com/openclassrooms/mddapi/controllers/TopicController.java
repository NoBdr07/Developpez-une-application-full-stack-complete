package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.mappers.TopicMapper;
import com.openclassrooms.mddapi.models.dtos.TopicDto;
import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.SubscriptionService;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    /**
     * Get all topics
     * @return List<TopicDto>
     */
    @GetMapping
    public List<TopicDto> getTopics() {
        return topicMapper.topicListToDto(topicService.getTopics());
    }

    /**
     * Get all topics the user has subscribed to
     * @return List<TopicDto>
     */
    @GetMapping("/subscriptions")
    public List<TopicDto> getSubscriptions() {
        Long userId = userDetailsService.getCurrentUserId();
        User user = userService.getUserFromId(userId).get();
        List<Topic> topics = subscriptionService.getSubscriptions(user);
        return topicMapper.topicListToDto(topics);
    }

    /**
     * Create a new topic
     * @param topicId
     */
    @PostMapping("/{topicId}/subscribe")
    public void subscribe(@PathVariable("topicId") Long topicId) {
        Long userId = userDetailsService.getCurrentUserId();
        User user = userService.getUserFromId(userId).get();

        Topic topic = topicService.getTopicById(topicId).get();

        topicService.subscribe(topic, user);
    }

    /**
     * Delete a topic
     * @param topicId
     */
    @DeleteMapping("/{topicId}/subscribe")
    public void unsubscribe(@PathVariable("topicId") Long topicId) {
        Long userId = userDetailsService.getCurrentUserId();
        User user = userService.getUserFromId(userId).get();

        Topic topic = topicService.getTopicById(topicId).get();

        topicService.unsubscribe(topic, user);
    }
}
