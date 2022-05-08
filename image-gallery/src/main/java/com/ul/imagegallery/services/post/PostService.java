package com.ul.imagegallery.services.post;

import com.ul.imagegallery.database.entity.Picture;
import com.ul.imagegallery.database.entity.Post;
import com.ul.imagegallery.database.entity.User;

import java.util.List;

public interface PostService {

    List<Post> getPostsForUser(String userName);

    boolean addPost(byte[] file, String authorName, String comment, boolean isForFriendsOnly, boolean isPrivate);

    List<Post> getUserPost(String userName);

    boolean addComment(Long commentID, String authorName, String value);
}
