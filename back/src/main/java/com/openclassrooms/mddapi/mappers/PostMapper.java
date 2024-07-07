package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.dtos.PostDto;
import com.openclassrooms.mddapi.models.entities.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

    /**
     * Maps a Post entity to a PostDto
     * @param post The Post entity to map
     * @return The PostDto
     */
    public PostDto postToDto(Post post) {
        if (post == null) {
            return null;
        }

        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUser(post.getUser());
        postDto.setTopic(post.getTopic());
        postDto.setCreatedAt(post.getCreatedAt());

        return postDto;
    }

    /** Maps a list of Post entities to a list of PostDtos
     * @param posts The list of Post entities to map
     * @return The list of PostDtos
     */
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

    /**
     * Maps a PostDto to a Post entity
     * @param postDto The PostDto to map
     * @return The Post entity
     */
    public Post dtoToPost(PostDto postDto) {
        if (postDto == null) {
            return null;
        }

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setTopic(postDto.getTopic());

        return post;
    }
}
