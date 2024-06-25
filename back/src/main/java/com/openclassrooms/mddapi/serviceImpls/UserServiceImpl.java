package com.openclassrooms.mddapi.serviceImpls;

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

    /**
     * Get user from id
     * @param userId User id
     * @return User
     */
    public Optional<User> getUserFromId(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Save user
     * @param user User
     * @return User
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Create user
     * @param email User email
     * @param password User password
     * @param username User username
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

    /**
     * Check if user exists by email
     * @param email User email
     * @return boolean
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Check if user exists by login
     * @param login User login, it can be email or username
     * @return User
     */
    public Optional<User> findByLogin(String login) {
        Optional<User> user = userRepository.findByEmail(login);
        if (user.isEmpty()) {
            user = userRepository.findByUsername(login);
        }
        return user;
    }

}
