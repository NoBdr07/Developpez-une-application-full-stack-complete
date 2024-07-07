package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.models.dtos.PostDto;
import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.services.SubscriptionService;
import com.openclassrooms.mddapi.services.UserService;
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
    private UserService userService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * Create a new post
     * @param postDto The postDto that contains the post content, the title and the topic.
     */
    @PostMapping
    public void createPost(@Valid @RequestBody PostDto postDto) {
        // get the user id
        Long userId = userDetailsService.getCurrentUserId();
        User user = userService.getUserFromId(userId).get();

        // create the post
        Post post = postMapper.dtoToPost(postDto);
        post.setUser(user);
        postService.createPost(post);
    }

    /**
     * Get all posts to create the feed of the user
     * We first get the user, then its subscriptions and finally the posts of the topics
     * @return List<PostDto>
     */
    @GetMapping
    public List<PostDto> getPosts() {
        // get the user
        Long userId = userDetailsService.getCurrentUserId();
        User user = userService.getUserFromId(userId).get();

        // get its subscriptions
        List<Topic> topics = subscriptionService.getSubscriptions(user);

        //get the posts
        return postMapper.postListToDto(postService.getPostsByTopics(topics));
    }

    /**
     * Get a post by its id
     * @param postId The id of the post
     * @return PostDto
     */
    @GetMapping("/{postId}")
    public PostDto getPost(@PathVariable("postId") Long postId) {
        return postMapper.postToDto(postService.getPost(postId).get());
    }
}
