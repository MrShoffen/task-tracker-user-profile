package org.mrshoffen.tasktracker.user.profile.util.mapper;

import org.mapstruct.Mapper;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserCreateDto;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.model.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateDto user);

    UserResponseDto toDto(User user);
}
