package com.openclassrooms.mddapi.serviceImpls;

import com.openclassrooms.mddapi.mappers.CommentMapper;
import com.openclassrooms.mddapi.models.entities.Comment;
import com.openclassrooms.mddapi.repositories.CommentRepository;
import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    public Optional<Comment> getCommentFromId(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public Comment saveComment(Comment comment) {
        String formattedDate = DateUtils.formatToMySQLDateTime(new Date());
        comment.setCreatedAt(formattedDate);
        return commentRepository.save(comment);
    }

    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
