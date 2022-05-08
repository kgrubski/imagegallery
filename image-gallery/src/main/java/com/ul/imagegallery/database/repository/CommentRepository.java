package com.ul.imagegallery.database.repository;

import com.ul.imagegallery.database.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
