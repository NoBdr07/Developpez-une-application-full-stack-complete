package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.dtos.UserDto;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * Get the current user
     * @return the current user
     */
    @GetMapping("/me")
    public UserDto getCurentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            User user = userService.findByLogin(username).get();

            return userMapper.userToDto(user);
        } else {
            // Handle the case where the Principal is not an instance of UserDetails
            throw new RuntimeException("Principal is not an instance of UserDetails");
        }
    }

    /**
     * Update the current user
     * @param userDto the user to update
     * @return the updated user
     */
    @PutMapping("/me")
    public UserDto updateCurrentUser(@Valid @RequestBody UserDto userDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        User user = userService.findByLogin(username).get();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        return userMapper.userToDto(userService.save(user));
    }
}
