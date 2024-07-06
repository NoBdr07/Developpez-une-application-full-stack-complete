package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.models.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTopicIn(List<Topic> topics);

    Optional<Post> findById(Long postId);
}
