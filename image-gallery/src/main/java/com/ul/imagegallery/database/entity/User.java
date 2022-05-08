package com.ul.imagegallery.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_friends")
    private Set<User> friend;
}
