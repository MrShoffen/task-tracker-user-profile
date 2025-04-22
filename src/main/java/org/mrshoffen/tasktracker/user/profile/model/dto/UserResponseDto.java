package org.mrshoffen.tasktracker.user.profile.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private UUID id;

    private String email;

    private String avatarUrl;

}
