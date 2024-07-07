package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.dtos.CommentDto;
import com.openclassrooms.mddapi.models.entities.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {
    /**
     * Maps a Comment entity to a CommentDto
     * @param comment the Comment entity to map
     * @return the CommentDto
     */
    public CommentDto commentToDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setContent(comment.getContent());
        commentDto.setUser(comment.getUser());
        commentDto.setPost(comment.getPost());
        commentDto.setCreatedAt(comment.getCreatedAt());

        return commentDto;
    }

    /**
     * Maps a list of Comment entities to a list of CommentDtos
     * @param comments the list of Comment entities to map
     * @return the list of CommentDtos
     */
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

    /**
     * Maps a CommentDto to a Comment entity
     * @param commentDto the CommentDto to map
     * @return the Comment entity
     */
    public Comment dtoToComment(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setCommentId(commentDto.getCommentId());
        comment.setContent(commentDto.getContent());
        if (commentDto.getUser() != null) {
            comment.setUser(commentDto.getUser());
        }
        if (commentDto.getPost() != null) {
            comment.setPost(commentDto.getPost());
        }
        comment.setCreatedAt(commentDto.getCreatedAt());

        return comment;
    }
}
