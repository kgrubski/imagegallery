package com.ul.imagegallery.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostDto {
    private MultipartFile picture;
    private String title;
    private byte[] bytePicture;
    private String author;
    private List<String> commentList;
    private int likeCounter;
    private String description;
    private Long id;
    private boolean isForFriendsOnly, isPrivate;

}
