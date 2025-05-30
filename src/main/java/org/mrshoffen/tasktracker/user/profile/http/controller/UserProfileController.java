package org.mrshoffen.tasktracker.user.profile.http.controller;


import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.commons.web.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.exception.UserNotFoundException;
import org.mrshoffen.tasktracker.user.profile.model.dto.AvatarEditDto;
import org.mrshoffen.tasktracker.user.profile.model.dto.ProfileEditDto;
import org.mrshoffen.tasktracker.user.profile.model.entity.User;
import org.mrshoffen.tasktracker.user.profile.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.mrshoffen.tasktracker.commons.web.authentication.AuthenticationAttributes.AUTHORIZED_USER_HEADER_NAME;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping("/me")
    ResponseEntity<UserResponseDto> me(@RequestHeader(AUTHORIZED_USER_HEADER_NAME) String userId) {
        return ResponseEntity.ok(
                userService.getUserById(UUID.fromString(userId))
        );
    }

    @PatchMapping("/me/information")
    ResponseEntity<UserResponseDto> updateProfileInformation(@RequestHeader(AUTHORIZED_USER_HEADER_NAME) UUID userId,
                                                             @RequestBody ProfileEditDto editDto) {
        return ResponseEntity.ok(
                userService.updateUserProfileInformation(userId, editDto)
        );
    }

    @PatchMapping("/me/avatar")
    ResponseEntity<UserResponseDto> updateAvatar(@RequestHeader(AUTHORIZED_USER_HEADER_NAME) UUID userId,
                                                 @RequestBody AvatarEditDto editDto) {
        return ResponseEntity.ok(
                userService.updateUserAvatar(userId, editDto.avatarUrl())
        );
    }

    @GetMapping("/info")
    ResponseEntity<UserResponseDto> userInformation(@RequestParam("id") UUID id) {
        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }

}
