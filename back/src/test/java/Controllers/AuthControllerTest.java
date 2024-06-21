package Controllers;

import com.openclassrooms.mddapi.controllers.AuthController;
import com.openclassrooms.mddapi.models.payload.LoginRequest;
import com.openclassrooms.mddapi.models.payload.RegisterRequest;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return successful registration message when user is registered successfully")
    public void registerUser_Success() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@test.com");
        registerRequest.setPassword("password");
        registerRequest.setUsername("username");

        when(userService.existsByEmail(any(String.class))).thenReturn(false);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        ResponseEntity<?> response = authController.registerUser(registerRequest);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return error message when email is already taken")
    public void registerUser_EmailAlreadyTaken() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@test.com");
        registerRequest.setPassword("password");
        registerRequest.setUsername("username");

        when(userService.existsByEmail(any(String.class))).thenReturn(true);

        ResponseEntity<?> response = authController.registerUser(registerRequest);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return JWT token when user is authenticated successfully")
    public void authenticateUser_Success() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin("username");
        loginRequest.setPassword("password");

        when(userDetailsService.loadUserByUsername(any(String.class))).thenReturn(mock(UserDetails.class));
        when(jwtUtils.generateJwtToken(any(Authentication.class))).thenReturn("jwtToken");

        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        assertEquals(200, response.getStatusCodeValue());
    }
}