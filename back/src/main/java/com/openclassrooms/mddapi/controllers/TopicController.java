package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.mappers.TopicMapper;
import com.openclassrooms.mddapi.models.dtos.TopicDto;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.TopicService;
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
    private TopicMapper topicMapper;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping
    public List<TopicDto> getTopics() {
        return topicMapper.topicListToDto(topicService.getTopics());
    }

    @PostMapping("/{topicId}/subscribe")
    public void subscribe(@PathVariable("topicId") Long topicId) {
        Long userId = userDetailsService.getCurrentUserId();

        topicService.subscribe(topicId, userId);
    }

    @DeleteMapping("/{topicId}/subscribe")
    public void unsubscribe(@PathVariable("topicId") Long topicId) {
        Long userId = userDetailsService.getCurrentUserId();

        topicService.unsubscribe(topicId, userId);
    }

}
