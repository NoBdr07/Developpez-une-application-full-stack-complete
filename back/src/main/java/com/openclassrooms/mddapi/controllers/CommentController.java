package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.mappers.CommentMapper;
import com.openclassrooms.mddapi.models.dtos.CommentDto;
import com.openclassrooms.mddapi.models.entities.Comment;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Create a new comment
     * @param postId
     * @param commentDto
     * @return CommentDto
     */
    @PostMapping("/posts/{postId}/comments")
    public CommentDto createComment(@PathVariable("postId") Long postId, @RequestBody CommentDto commentDto) {
        Comment comment = commentMapper.dtoToComment(commentDto);
        comment.setPostId(postId);
        comment.setUserId(userDetailsService.getCurrentUserId());
        return commentMapper.commentToDto(commentService.saveComment(comment));
    }

    /**
     * Get all comments for a post
     * @param postId
     * @return List<CommentDto>
     */
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable("postId") Long postId) {
        return commentMapper.commentListToDto(commentService.findByPostId(postId));
    }
}
