package com.ul.imagegallery.services.post;

import com.ul.imagegallery.database.entity.Comment;
import com.ul.imagegallery.database.entity.Picture;
import com.ul.imagegallery.database.entity.Post;
import com.ul.imagegallery.database.entity.User;
import com.ul.imagegallery.database.repository.PostRepository;
import com.ul.imagegallery.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public List<Post> getPostsForUser(String userName) {
        List<Post> postList = postRepository.findAll();
        List<Post> filteredPostList = new ArrayList<>();
        User user = userRepository.findByUsername(userName).orElse(null);
        for (Post post : postList) {
            if (!post.isForFriendsOnly() && !post.isPrivate()) {
                filteredPostList.add(post);
                break;
            }

            if (post.isPrivate() && post.getAuthor().equals(user)) {
                filteredPostList.add(post);
                break;
            }

            if (post.isForFriendsOnly() && post.getAuthor().getFriend().contains(user)) { // todo
                filteredPostList.add(post);
                break;
            }
        }
        return filteredPostList;
    }


    @Override
    public boolean addPost(byte[] file, String authorName, String title, String description, boolean isForFriendsOnly, boolean isPrivate) {
        Optional<User> user = userRepository.findByUsername(authorName);
        if (user.isEmpty()) {
            return false;
        }

        Picture picture = new Picture();
        picture.setImage(file);
        Post post = new Post();
        post.setAuthor(user.get());
        post.setForFriendsOnly(isForFriendsOnly);
        post.setTitle(title);
        post.setDescription(description);
        post.setPrivate(isPrivate);
        post.setPicture(picture);

        postRepository.save(post); // todo add picture repository?
        return true;
    }

    @Override
    public List<Post> getUserPost(String userName) {
        User user = userRepository.findByUsername(userName).orElse(null);
        return postRepository.findByAuthor(user);
    }

    @Override
    public boolean addComment(Long commentID, String authorName, String value) {
        Optional<Post> postFromDb = postRepository.findById(commentID);

        if (postFromDb.isEmpty()) {
            return false;
        }

        Optional<User> user = userRepository.findByUsername(authorName);

        if (user.isEmpty()) {
            return false;
        }

        Post post = postFromDb.get();
        Set<Comment> commentList = post.getCommentList();
        if (commentList == null) {
            commentList = new HashSet<>();
            post.setCommentList(commentList);
        }
        Comment comment = new Comment();
        comment.setAuthor(user.get());
        comment.setPost(post);
        comment.setValue(value);

        postRepository.save(post); // todo add commentRepo?

        return true;
    }

    @Override
    public boolean likeOrUnlikeComment(Long commentID, String userName) {
        Optional<Post> postFromDb = postRepository.findById(commentID);

        if (postFromDb.isEmpty()) {
            return false;
        }

        Optional<User> user = userRepository.findByUsername(userName);

        if (user.isEmpty()) {
            return false;
        }

        Post post = postFromDb.get();
        List<User> likedBy = post.getLikedBy();
        if (likedBy.contains(user.get())) {
            likedBy.remove(user.get());
            post.setLikeCounter(post.getLikeCounter() - 1);
            postRepository.save(post);
        }
        return true;
    }

    @Override
    public Post getPostByID(Long commentID) {
        Optional<Post> postFromDb = postRepository.findById(commentID);

        if (postFromDb.isEmpty()) {
            return null;
        }
        return postFromDb.get();
    }
}
