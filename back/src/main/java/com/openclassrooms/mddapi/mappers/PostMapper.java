package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.dtos.PostDto;
import com.openclassrooms.mddapi.models.entities.Post;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    public PostDto postToDto(Post post) {
        if (post == null) {
            return null;
        }

        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUser(userService.getUserFromId(post.getUserId()).get());
        postDto.setTopic(topicService.getTopicById(post.getTopicId()).get());
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
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setTopicId(postDto.getTopic().getTopicId());

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
