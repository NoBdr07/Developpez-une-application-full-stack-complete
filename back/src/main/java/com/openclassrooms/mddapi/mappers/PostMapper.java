package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.dtos.PostDto;
import com.openclassrooms.mddapi.models.entities.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

    public PostDto postToDto(Post post) {
        if (post == null) {
            return null;
        }

        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUserId(post.getUserId());
        postDto.setTopicId(post.getTopicId());
        postDto.setCreatedAt(post.getCreatedAt());

        return postDto;
    }

    public List<PostDto> postListToDto(List<Post> posts) {
        List<PostDto> postDtos = new ArrayList<>();

        for (Post post : posts) {
            PostDto postDto = postToDto(post);

            if (postDto != null) {
                postDtos.add(postDto);
            }
        }
        return postDtos;
    }

    public Post dtoToPost(PostDto postDto) {
        if (postDto == null) {
            return null;
        }

        Post post = new Post();
        post.setPostId(postDto.getPostId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUserId(postDto.getUserId());
        post.setTopicId(postDto.getTopicId());
        post.setCreatedAt(postDto.getCreatedAt());

        return post;
    }

    public List<Post> dtoListToPost(List<PostDto> postDtos) {
        List<Post> posts = new ArrayList<>();

        for (PostDto postDto : postDtos) {
            Post post = dtoToPost(postDto);

            if (post != null) {
                posts.add(post);
            }
        }
        return posts;
    }
}
