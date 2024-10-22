package com.rafael.app.blogru.security.mapper;

import com.rafael.app.blogru.security.document.User;
import com.rafael.app.blogru.security.dto.UserDto;

public class UserMapper {

    public static UserDto mapUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
