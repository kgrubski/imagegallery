package com.ul.imagegallery.services.post;

import com.ul.imagegallery.database.entity.Post;
import com.ul.imagegallery.model.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getPostsForUser(String userName);

    boolean addPost(byte[] file, String authorName, String title, String description, boolean isForFriendsOnly, boolean isPrivate);

    List<Post> getUserPost(String userName);

    boolean addComment(Long commentID, String authorName, String value);

    boolean likeOrUnlikeComment(Long commentID, String userName);

    Post getPostByID(Long commentID);
}
