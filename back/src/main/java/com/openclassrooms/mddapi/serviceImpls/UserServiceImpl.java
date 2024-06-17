package com.openclassrooms.mddapi.serviceImpls;

import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.services.UserService;
import com.openclassrooms.mddapi.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public Optional<User> getUserFromId(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Save user
     *
     * @param user User to save
     * @return User
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Get user from email
     *
     * @param email Email of the user
     * @return Optional<User>
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Create user
     *
     * @param email email of user
     * @param password password encoded
     * @param username name of user
     * @return User
     */
    public User create(String email, String password, String username) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setUsername(username);

        String formattedDate = DateUtils.formatToMySQLDateTime(new Date());
        newUser.setCreatedAt(formattedDate);

        return userRepository.save(newUser);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findByLogin(String login) {
        Optional<User> user = userRepository.findByEmail(login);
        if (user.isEmpty()) {
            user = userRepository.findByUsername(login);
        }
        return user;
    }

}
