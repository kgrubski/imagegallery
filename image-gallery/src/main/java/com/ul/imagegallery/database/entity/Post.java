package com.ul.imagegallery.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @OneToMany
    private Set<Comment> commentList;

    @OneToOne
    @JoinColumn(name = "author_id")
    private User author;

    private int likeCounter = 0;

    private boolean isPrivate = false;

    private boolean isForFriendsOnly = false;

    private String title;

    private String description;

    @OneToMany
    private List<User> likedBy;
}
