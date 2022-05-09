package com.ul.imagegallery.util.mapper;

import com.ul.imagegallery.database.entity.Comment;
import com.ul.imagegallery.database.entity.Post;
import com.ul.imagegallery.model.PostDto;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto mapFromPost(Post post){
        PostDto postDto = new PostDto();
        postDto.setAuthor(post.getAuthor().getUsername());
        postDto.setBytePicture(post.getPicture().getImage());
        List<String> comments = post.getCommentList().stream().map(Comment::getValue).collect(Collectors.toList());
        postDto.setCommentList(comments);
        postDto.setDescription(post.getDescription());
        postDto.setLikeCounter(post.getLikeCounter());
        postDto.setId(post.getId());
        postDto.setForFriendsOnly(post.isForFriendsOnly());
        postDto.setPrivate(post.isPrivate());
        return postDto;
    }

}
