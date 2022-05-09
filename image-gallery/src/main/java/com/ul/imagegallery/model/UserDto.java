package com.ul.imagegallery.model;

import com.sun.istack.NotNull;
import com.ul.imagegallery.util.ImageUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;
    private String matchingPassword;

    @NotNull
    private String email;

}