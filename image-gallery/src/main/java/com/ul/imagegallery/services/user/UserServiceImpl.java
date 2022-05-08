package com.ul.imagegallery.services.user;

import com.ul.imagegallery.database.entity.User;
import com.ul.imagegallery.database.repository.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            log.error("User {} not found", username);
            return null;
        }
        return user.get();
    }

    @Override
    public User saveUser(String username, String password) {
        User user = findUserByUsername(username);
        if (user == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            return userRepository.save(newUser);
        }
        log.debug("User {} already exist!", username);
        return null;
    }

    @Override
    public boolean followUser(String user, String followedUser) {
        Optional<User> currentUser = userRepository.findByUsername(user);
        Optional<User> followedUserFromDb = userRepository.findByUsername(followedUser);
        if (currentUser.isEmpty() || followedUserFromDb.isEmpty()) {
            return false;
        }
        currentUser.get().getFriend().add(followedUserFromDb.get());
        return true;

    }


}
