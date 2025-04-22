package org.mrshoffen.tasktracker.user.profile.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserCreateDto;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User toEntity(UserCreateDto user);

    public abstract UserResponseDto toDto(User user);
}
