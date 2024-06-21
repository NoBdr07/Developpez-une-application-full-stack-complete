package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserFromId(Long userId);

    User save(User user);

    User create(String email, String password, String name);

    boolean existsByEmail(String email);

    Optional<User>  findByLogin(String login);


}
