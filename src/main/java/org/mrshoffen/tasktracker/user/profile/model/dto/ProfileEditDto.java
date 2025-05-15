package org.mrshoffen.tasktracker.user.profile.model.dto;

public record ProfileEditDto(
        String firstName,
        String lastName,
        String country,
        String region
) {
}
