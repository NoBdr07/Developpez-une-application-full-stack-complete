package Controllers;

import com.openclassrooms.mddapi.controllers.PostController;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.models.dtos.PostDto;
import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.services.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostService postService;

    @Mock
    private PostMapper postMapper;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private SubscriptionService subscriptionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create post successfully")
    public void createPost_Success() {
        PostDto postDto = new PostDto();
        postDto.setContent("Test content");

        when(userDetailsService.getCurrentUserId()).thenReturn(1L);
        when(postMapper.dtoToPost(any(PostDto.class))).thenReturn(new Post());

        postController.createPost(postDto);
    }

    @Test
    @DisplayName("Should return list of posts when posts are fetched")
    public void getPosts_Success() {
        PostDto postDto = new PostDto();
        postDto.setContent("Test content");

        when(userDetailsService.getCurrentUserId()).thenReturn(1L);
        when(subscriptionService.getSubscriptions(any(Long.class))).thenReturn(Collections.singletonList(1L));
        when(postService.getPostsByTopicIds(any(List.class))).thenReturn(Collections.singletonList(new Post()));
        when(postMapper.postListToDto(any(List.class))).thenReturn(Collections.singletonList(postDto));

        List<PostDto> response = postController.getPosts();

        assertEquals(1, response.size());
        assertEquals(postDto.getContent(), response.get(0).getContent());
    }

    @Test
    @DisplayName("Should return post when post is fetched by id")
    public void getPost_Success() {
        PostDto postDto = new PostDto();
        postDto.setContent("Test content");

        when(postService.getPost(any(Long.class))).thenReturn(java.util.Optional.of(new Post()));
        when(postMapper.postToDto(any(Post.class))).thenReturn(postDto);

        PostDto response = postController.getPost(1L);

        assertEquals(postDto.getContent(), response.getContent());
    }
}