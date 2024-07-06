package com.openclassrooms.mddapi.serviceImpls;

import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.repositories.PostRepository;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * Create a new post
     * @param post A Post object that only contains the title, content, and subject
     *             The other fields will be set by the service
     * @return The created Post object
     */
    public Post createPost(Post post) {
        String formattedDate = DateUtils.formatToMySQLDateTime(new Date());
        post.setCreatedAt(formattedDate);
        return postRepository.save(post);
    }

    /**
     * Get all posts that belong to the topics the user has subscribed to
     * @param topics A list of topics
     * @return A list of Post objects
     */
    public List<Post> getPostsByTopics(List<Topic> topics) {
        return postRepository.findByTopicIn(topics);
    }

    /**
     * Get a post by its ID
     * @param postId The ID of the post
     * @return A Post object
     */
    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }
}
