package Controllers;

import com.openclassrooms.mddapi.controllers.CommentController;
import com.openclassrooms.mddapi.mappers.CommentMapper;
import com.openclassrooms.mddapi.models.dtos.CommentDto;
import com.openclassrooms.mddapi.models.entities.Comment;
import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @Mock
    private PostService postService;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private UserService userService;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @InjectMocks
    private CommentController commentController;

    private User testUser;
    private Post testPost;
    private Comment testComment;
    private CommentDto testCommentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("password");
        testUser.setUsername("testuser");

        testPost = new Post();
        testPost.setPostId(1L);
        testPost.setContent("Test Content");
        testPost.setTitle("Test Title");

        testComment = new Comment();
        testComment.setCommentId(1L);
        testComment.setContent("Test Comment");
        testComment.setPost(testPost);
        testComment.setUser(testUser);

        testCommentDto = new CommentDto();
        testCommentDto.setContent("Test Comment");
    }

    @Test
    void createComment_ShouldReturnCommentDto() {
        when(postService.getPost(1L)).thenReturn(Optional.of(testPost));
        when(userService.getUserFromId(1L)).thenReturn(Optional.of(testUser));
        when(userDetailsService.getCurrentUserId()).thenReturn(1L);
        when(commentMapper.dtoToComment(any(CommentDto.class))).thenReturn(testComment);
        when(commentService.saveComment(any(Comment.class))).thenReturn(testComment);
        when(commentMapper.commentToDto(any(Comment.class))).thenReturn(testCommentDto);

        CommentDto result = commentController.createComment(1L, testCommentDto);

        assertEquals("Test Comment", result.getContent());
        verify(commentService, times(1)).saveComment(any(Comment.class));
    }

    @Test
    void getCommentsByPostId_ShouldReturnListOfCommentDtos() {
        when(postService.getPost(1L)).thenReturn(Optional.of(testPost));
        when(commentService.findByPost(any(Post.class))).thenReturn(Arrays.asList(testComment));
        when(commentMapper.commentListToDto(any(List.class))).thenReturn(Arrays.asList(testCommentDto));

        List<CommentDto> result = commentController.getCommentsByPostId(1L);

        assertEquals(1, result.size());
        assertEquals("Test Comment", result.get(0).getContent());
        verify(commentService, times(1)).findByPost(any(Post.class));
    }
}
