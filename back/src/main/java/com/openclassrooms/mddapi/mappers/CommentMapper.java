package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.dtos.CommentDto;
import com.openclassrooms.mddapi.models.entities.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {

    public CommentDto commentToDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setContent(comment.getContent());
        commentDto.setUserId(comment.getUserId());
        commentDto.setPostId(comment.getPostId());
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
}
