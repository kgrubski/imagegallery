package com.ul.imagegallery.services.user;

import com.ul.imagegallery.database.entity.User;

public interface UserService {

    User findUserByUsername(String username) throws Exception;

    User saveUser(String user, String password);

    boolean followUser(String user, String followedUser);
}
