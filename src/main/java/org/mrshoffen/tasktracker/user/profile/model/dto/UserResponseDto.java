package org.mrshoffen.tasktracker.user.profile.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    String email;
    String firstName;
    String lastName;
    String avatarUrl;
    String country;
    String region;
}