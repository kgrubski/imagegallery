package com.ul.imagegallery.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private UserDto author;
    private String value;

}
