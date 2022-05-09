package com.ul.imagegallery.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "longblob")
    private byte[] image;

}
