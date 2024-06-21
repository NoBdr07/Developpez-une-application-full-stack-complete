package Controllers;

import com.openclassrooms.mddapi.controllers.UserController;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.dtos.UserDto;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return current user when user is fetched successfully")
    public void getCurrentUser_Success() {
        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setEmail("test@test.com");

        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("username");
        when(userService.findByLogin(any(String.class))).thenReturn(java.util.Optional.of(new User()));
        when(userMapper.userToDto(any(User.class))).thenReturn(userDto);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDto response = userController.getCurentUser();

        assertEquals(userDto.getUsername(), response.getUsername());
        assertEquals(userDto.getEmail(), response.getEmail());
    }

    @Test
    @DisplayName("Should return updated user when user is updated successfully")
    public void updateCurrentUser_Success() {
        UserDto userDto = new UserDto();
        userDto.setUsername("newUsername");
        userDto.setEmail("newTest@test.com");

        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("username");
        when(userService.findByLogin(any(String.class))).thenReturn(java.util.Optional.of(new User()));
        when(userService.save(any(User.class))).thenReturn(new User());
        when(userMapper.userToDto(any(User.class))).thenReturn(userDto);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDto response = userController.updateCurrentUser(userDto);

        assertEquals(userDto.getUsername(), response.getUsername());
        assertEquals(userDto.getEmail(), response.getEmail());
    }
}
