package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.payload.JwtResponse;
import com.openclassrooms.mddapi.models.payload.LoginRequest;
import com.openclassrooms.mddapi.models.payload.MessageResponse;
import com.openclassrooms.mddapi.models.payload.RegisterRequest;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService ;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        userService.create(registerRequest.getEmail(), passwordEncoder.encode(registerRequest.getPassword()), registerRequest.getUsername());

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getLogin());
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword())
        );

        String jwt = jwtUtils.generateJwtToken(auth);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok(new MessageResponse("User logout successfully!"));
    }

}
