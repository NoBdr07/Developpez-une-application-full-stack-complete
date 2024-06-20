package com.openclassrooms.mddapi.models.dtos;


import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.models.entities.User;
import org.springframework.lang.NonNull;

public class CommentDto {
    private Long commentId;

    @NonNull
    private String content;

    private String createdAt;

    private User user;

    private Post post;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
