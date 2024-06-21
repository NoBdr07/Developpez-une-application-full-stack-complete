package Controllers;

import com.openclassrooms.mddapi.controllers.CommentController;
import com.openclassrooms.mddapi.mappers.CommentMapper;
import com.openclassrooms.mddapi.models.dtos.CommentDto;
import com.openclassrooms.mddapi.models.entities.Comment;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CommentControllerTest {

    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentService commentService;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private PostService postService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createComment_Success() {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Test content");

        when(commentMapper.dtoToComment(any(CommentDto.class))).thenReturn(new Comment());
        when(userDetailsService.getCurrentUserId()).thenReturn(1L);
        when(commentService.saveComment(any(Comment.class))).thenReturn(new Comment());
        when(commentMapper.commentToDto(any(Comment.class))).thenReturn(commentDto);

        CommentDto response = commentController.createComment(1L, commentDto);

        assertEquals(commentDto.getContent(), response.getContent());
    }

    @Test
    public void getCommentsByPostId_Success() {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Test content");

        when(commentService.findByPostId(any(Long.class))).thenReturn(Collections.singletonList(new Comment()));
        when(commentMapper.commentListToDto(any(List.class))).thenReturn(Collections.singletonList(commentDto));

        List<CommentDto> response = commentController.getCommentsByPostId(1L);

        assertEquals(1, response.size());
        assertEquals(commentDto.getContent(), response.get(0).getContent());
    }
}