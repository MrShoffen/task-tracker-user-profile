package org.mrshoffen.tasktracker.user.profile.http.controller;


import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.mrshoffen.tasktracker.commons.web.authentication.AuthenticationAttributes.AUTHORIZED_USER_HEADER_NAME;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping("/me")
    ResponseEntity<UserResponseDto> me(@RequestHeader(AUTHORIZED_USER_HEADER_NAME) String userId){
        return ResponseEntity.ok(
                userService.getUserById(UUID.fromString(userId))
        );
    }

}
