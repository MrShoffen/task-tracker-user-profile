package org.mrshoffen.tasktracker.user.profile.http.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/users/id")
public class UserValidationController {

    private final UserService userService;

    @GetMapping
    ResponseEntity<String> validateUserCredentials(@RequestParam("email") String email,
                                                @RequestParam("password") String password) {
        UserResponseDto user = userService.getUser(email, password);
        log.info("User {} found! ", email);

        return ResponseEntity.ok(user.getId().toString());
    }

}
