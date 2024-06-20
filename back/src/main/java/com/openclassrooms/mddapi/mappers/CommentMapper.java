package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.dtos.CommentDto;
import com.openclassrooms.mddapi.models.entities.Comment;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    public CommentDto commentToDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setContent(comment.getContent());
        commentDto.setUser(userService.getUserFromId(comment.getUserId()).get());
        commentDto.setPost(postService.getPost(comment.getPostId()).get());
        commentDto.setCreatedAt(comment.getCreatedAt());

        return commentDto;
    }

    public List<CommentDto> commentListToDto(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentDto commentDto = commentToDto(comment);

            if (commentDto != null) {
                commentDtos.add(commentDto);
            }
        }
        return commentDtos;
    }

    public Comment dtoToComment(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setCommentId(commentDto.getCommentId());
        comment.setContent(commentDto.getContent());
        if (commentDto.getUser() != null) {
            comment.setUserId(commentDto.getUser().getId());
        }
        if (commentDto.getPost() != null) {
            comment.setPostId(commentDto.getPost().getPostId());
        }
        comment.setCreatedAt(commentDto.getCreatedAt());

        return comment;
    }

    public List<Comment> dtoListToComment(List<CommentDto> commentDtos) {
        List<Comment> comments = new ArrayList<>();

        for (CommentDto commentDto : commentDtos) {
            Comment comment = dtoToComment(commentDto);

            if (comment != null) {
                comments.add(comment);
            }
        }
        return comments;
    }
}
