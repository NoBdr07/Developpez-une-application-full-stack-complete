package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> getPostsByTopicIds(List<Long> topicIds);

    Post createPost(Post post);

    Optional<Post> getPost(Long postId);
}
