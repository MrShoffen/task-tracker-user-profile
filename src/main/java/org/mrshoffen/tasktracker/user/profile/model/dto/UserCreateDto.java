package org.mrshoffen.tasktracker.user.profile.model.dto;

import org.mrshoffen.tasktracker.user.profile.util.validation.ValidPassword;
import org.mrshoffen.tasktracker.user.profile.util.validation.ValidEmail;

public record UserCreateDto(
        @ValidEmail
        String email,

        @ValidPassword
        String password,

        String avatarUrl
) {
}
