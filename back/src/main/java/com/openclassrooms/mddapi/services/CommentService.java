package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.entities.Comment;
import com.openclassrooms.mddapi.models.entities.Post;

import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);

    List<Comment> findByPost(Post post);

}
