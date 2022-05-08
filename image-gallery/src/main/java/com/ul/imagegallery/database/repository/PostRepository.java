package com.ul.imagegallery.database.repository;

import com.ul.imagegallery.database.entity.Post;
import com.ul.imagegallery.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthor(User author);
}
