package com.weg.library.mapper;

import com.weg.library.dto.user.UserRequestDto;
import com.weg.library.dto.user.UserResponseDto;
import com.weg.library.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto userRequestDto){
        return new User(userRequestDto.name(), userRequestDto.email());
    }

    public UserResponseDto toDto(User user){
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }
}
