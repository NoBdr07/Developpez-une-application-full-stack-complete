package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.entities.Comment;

import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);

    List<Comment> findByPostId(Long postId);

}
