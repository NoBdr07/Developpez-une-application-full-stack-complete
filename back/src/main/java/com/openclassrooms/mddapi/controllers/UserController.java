package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.dtos.UserDto;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.models.payload.JwtResponse;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.openclassrooms.mddapi.models.payload.JwtResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

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
     * Update the current user. It save the new user is database and then generate a new JWT token
     * with the updated user information.
     * It is necessary to make sure that the authentication process will work
     * once the user has updated its information.
     * @param userDto the user to update
     * @return the updated user
     */
    @PutMapping("/me")
    public ResponseEntity<?> updateCurrentUser(@Valid @RequestBody UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String currentUsername = userDetails.getUsername();

        User user = userService.findByLogin(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Update user details
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        // Save the updated user information
        User updatedUser = userService.save(user);

        // Reload the updated user details using the new username
        UserDetails updatedUserDetails = customUserDetailsService.loadUserByUsername(updatedUser.getUsername());

        // Update authentication with the new information
        Authentication newAuth = new UsernamePasswordAuthenticationToken(updatedUserDetails, null, updatedUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        // Generate a new JWT
        String newJwt = jwtUtils.generateJwtToken(newAuth);

        // Return the new JWT in the response header
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + newJwt);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new JwtResponse(newJwt));
    }
}
