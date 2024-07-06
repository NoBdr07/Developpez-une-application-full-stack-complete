package Controllers;

import com.openclassrooms.mddapi.controllers.PostController;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.models.dtos.PostDto;
import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.services.SubscriptionService;
import com.openclassrooms.mddapi.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Mock
    private PostMapper postMapper;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private PostController postController;

    private User testUser;
    private Topic testTopic;
    private Post testPost;
    private PostDto testPostDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("password");
        testUser.setUsername("testuser");

        testTopic = new Topic();
        testTopic.setTopicId(1L);
        testTopic.setName("Test Topic");

        testPost = new Post();
        testPost.setPostId(1L);
        testPost.setContent("Test Content");
        testPost.setTitle("Test Title");
        testPost.setUser(testUser);
        testPost.setTopic(testTopic);

        testPostDto = new PostDto();
        testPostDto.setContent("Test Content");
        testPostDto.setTitle("Test Title");
    }

    @Test
    void createPost_ShouldInvokeService() {
        when(userDetailsService.getCurrentUserId()).thenReturn(1L);
        when(userService.getUserFromId(1L)).thenReturn(Optional.of(testUser));
        when(postMapper.dtoToPost(any(PostDto.class))).thenReturn(testPost);

        postController.createPost(testPostDto);

        verify(postService, times(1)).createPost(any(Post.class));
    }

    @Test
    void getPosts_ShouldReturnListOfPostDtos() {
        when(userDetailsService.getCurrentUserId()).thenReturn(1L);
        when(userService.getUserFromId(1L)).thenReturn(Optional.of(testUser));
        when(subscriptionService.getSubscriptions(testUser)).thenReturn(Arrays.asList(testTopic));
        when(postService.getPostsByTopics(Arrays.asList(testTopic))).thenReturn(Arrays.asList(testPost));
        when(postMapper.postListToDto(any(List.class))).thenReturn(Arrays.asList(testPostDto));

        List<PostDto> result = postController.getPosts();

        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitle());
        assertEquals("Test Content", result.get(0).getContent());
    }

    @Test
    void getPost_ShouldReturnPostDto() {
        when(postService.getPost(1L)).thenReturn(Optional.of(testPost));
        when(postMapper.postToDto(any(Post.class))).thenReturn(testPostDto);

        PostDto result = postController.getPost(1L);

        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Content", result.getContent());
    }
}