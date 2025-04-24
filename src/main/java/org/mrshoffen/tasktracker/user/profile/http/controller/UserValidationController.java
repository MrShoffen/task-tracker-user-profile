package org.mrshoffen.tasktracker.user.profile.http.controller;

import lombok.RequiredArgsConstructor;
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
@RestController
@RequestMapping("/users/validate")
public class UserValidationController {

    private final UserService userService;

    private final Environment env;


    @GetMapping
    ResponseEntity<UUID> getUserId(@RequestParam("email") String email,
                                   @RequestParam("password") String password) {

        UserResponseDto user = userService.getUser(email, password);

        return ResponseEntity.ok(user.getId());
    }

}
