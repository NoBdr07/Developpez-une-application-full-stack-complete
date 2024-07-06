package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.models.entities.Topic;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> getPostsByTopics(List<Topic> topics);

    Post createPost(Post post);

    Optional<Post> getPost(Long postId);
}
