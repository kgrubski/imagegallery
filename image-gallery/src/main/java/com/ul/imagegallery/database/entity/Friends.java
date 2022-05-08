package com.ul.imagegallery.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_one_id")
    private User userOne;

    @OneToOne
    @JoinColumn(name = "user_two_id")
    private User userTwo;
}
