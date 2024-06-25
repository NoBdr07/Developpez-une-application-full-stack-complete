package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.dtos.UserDto;
import com.openclassrooms.mddapi.models.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /** Maps a User entity to a UserDto
     * @param user The User entity to map
     * @return The UserDto
     */
    public UserDto userToDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setCreatedAt(user.getCreatedAt());

        return userDto;
    }
}

