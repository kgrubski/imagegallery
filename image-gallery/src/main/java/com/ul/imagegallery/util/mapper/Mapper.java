package com.ul.imagegallery.util.mapper;

import com.ul.imagegallery.database.entity.Comment;
import com.ul.imagegallery.database.entity.Post;
import com.ul.imagegallery.database.entity.User;
import com.ul.imagegallery.model.CommentDto;
import com.ul.imagegallery.model.PostDto;
import com.ul.imagegallery.model.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Mapper {

    public static PostDto mapFromPost(Post post){
        PostDto postDto = new PostDto();
        List<CommentDto> commentDtoList = mapFromComment(post.getCommentList());
        postDto.setAuthor(post.getAuthor().getUsername());
        postDto.setBytePicture(post.getPicture().getImage());
        postDto.setCommentList(commentDtoList);
        postDto.setDescription(post.getDescription());
        postDto.setLikeCounter(post.getLikeCounter());
        postDto.setId(post.getId());
        postDto.setForFriendsOnly(post.isForFriendsOnly());
        postDto.setPrivate(post.isPrivate());
        return postDto;
    }


    private static List<CommentDto> mapFromComment(Set<Comment> commentList){
       return commentList.stream()
                .map(comment -> CommentDto.builder()
                        .author(mapFromUser(comment.getAuthor()))
                        .value(comment.getValue())
                        .build()
                ).collect(Collectors.toList());
    }

    private static UserDto mapFromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getUsername());
        return userDto;
    }

}
