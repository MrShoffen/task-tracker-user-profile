package org.mrshoffen.tasktracker.user.profile.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationSuccessfulEvent;
import org.mrshoffen.tasktracker.commons.web.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.model.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    @Mapping(source = "registrationId", target = "id")
    User userFromEvent(RegistrationSuccessfulEvent event);
}
