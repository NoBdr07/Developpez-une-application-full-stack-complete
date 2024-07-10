package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.payload.JwtResponse;
import com.openclassrooms.mddapi.models.payload.LoginRequest;
import com.openclassrooms.mddapi.models.payload.MessageResponse;
import com.openclassrooms.mddapi.models.payload.RegisterRequest;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService ;

    /**
     * Register user
     * @param registerRequest that contains email, username and password
     * @return ResponseEntity<?> that contains a message of successfully registration
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : email déjà pris !"));
        }
        if(userService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : nom d'utilisateur déjà pris !"));
        }
        userService.create(
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getUsername());

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    /**
     * Authenticate user
     * @param loginRequest that contains login and password
     *                     login can be email or username
     * @return ResponseEntity<?>
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword())
        );
        String jwt = jwtUtils.generateJwtToken(auth);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    /**
     * Logout user
     * @return ResponseEntity<?> that contains a message of successfully logout
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok(new MessageResponse("User logout successfully!"));
    }
}
