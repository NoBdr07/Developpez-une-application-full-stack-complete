package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> getCommentFromId(Long commentId);

    Comment saveComment(Comment comment);

    List<Comment> findByPostId(Long postId);

}
