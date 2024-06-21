package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.models.dtos.PostDto;
import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private SubscriptionService subscriptionService;

    // Create a new post related to a specific user and a specific topic
    @PostMapping
    public void createPost(@Valid @RequestBody PostDto postDto) {
        // get the user id
        Long userId = userDetailsService.getCurrentUserId();

        // create the post
        Post post = postMapper.dtoToPost(postDto);
        post.setUserId(userId);
        postService.createPost(post);
    }

    // Get all posts related to the user's subscriptions
    @GetMapping
    public List<PostDto> getPosts() {
        // get the user id
        Long userId = userDetailsService.getCurrentUserId();

        // get its subscriptions
        List<Long> topicIds = subscriptionService.getSubscriptions(userId);

        //get the posts
        return postMapper.postListToDto(postService.getPostsByTopicIds(topicIds));
    }

    // Get a specific post
    @GetMapping("/{postId}")
    public PostDto getPost(@PathVariable("postId") Long postId) {
        return postMapper.postToDto(postService.getPost(postId).get());
    }
}
