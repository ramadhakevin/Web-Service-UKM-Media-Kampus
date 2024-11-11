package com.mediakampus.mapper;

import com.mediakampus.dto.UserDto;
import com.mediakampus.entity.User;

public class UserMapper {

    // Mengonversi UserDto ke User (entity)
    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .nama(userDto.getNama())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .email(userDto.getEmail())
                .build();
    }

    // Mengonversi User (entity) ke UserDto
    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nama(user.getNama())
                .password(user.getPassword())
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }
}
