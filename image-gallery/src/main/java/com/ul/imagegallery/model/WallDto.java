package com.ul.imagegallery.model;

import com.ul.imagegallery.util.ImageUtil;
import lombok.Data;

import java.util.List;

@Data
public class WallDto {
    List<PostDto> postDtoList;
    ImageUtil imgUtil;
}
