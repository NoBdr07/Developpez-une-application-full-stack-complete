package com.openclassrooms.mddapi.serviceImpls;

import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.repositories.PostRepository;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        String formattedDate = DateUtils.formatToMySQLDateTime(new Date());
        post.setCreatedAt(formattedDate);
        return postRepository.save(post);
    }

    public List<Post> getPostsByTopicIds(List<Long> topicIds) {
        return postRepository.findByTopicIdIn(topicIds);
    }

    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }
}
