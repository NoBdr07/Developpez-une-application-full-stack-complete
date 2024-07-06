package com.openclassrooms.mddapi.serviceImpls;

import com.openclassrooms.mddapi.models.entities.Comment;
import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.repositories.CommentRepository;
import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * Save a comment
     * @param comment A comment object that only contains the content
     *                the rest of the fields will be set by the method
     * @return Comment
     */
    public Comment saveComment(Comment comment) {
        String formattedDate = DateUtils.formatToMySQLDateTime(new Date());
        comment.setCreatedAt(formattedDate);
        return commentRepository.save(comment);
    }

    /**
     * Find comments by post id
     * @param post The post concerned
     * @return List<Comment>
     */
    public List<Comment> findByPost(Post post) {

        return commentRepository.findByPost(post);
    }
}
