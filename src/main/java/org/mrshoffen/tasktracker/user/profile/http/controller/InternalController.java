package org.mrshoffen.tasktracker.user.profile.http.controller;

import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.commons.web.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.exception.UserNotFoundException;
import org.mrshoffen.tasktracker.user.profile.model.entity.User;
import org.mrshoffen.tasktracker.user.profile.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

/*
 * Эндпоинты только для межсервисного взаимодействия
 * */
@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/users")
public class InternalController {

    private final UserService userService;

    @GetMapping("/hashed-password")
    String userHashedPassword(@RequestParam("email") String email) {
        return userService.findUserByEmail(email)
                .map(User::getHashedPassword)
                .orElseThrow(
                        () -> new UserNotFoundException("Пользователь с почтой %s не найден".formatted(email)));
    }

    @GetMapping("/id")
    String userId(@RequestParam("email") String email) {
        return userService.findUserByEmail(email)
                .map(user -> user.getId().toString())
                .orElseThrow(
                        () -> new UserNotFoundException("Пользователь с почтой %s не найден".formatted(email)));
    }

    @GetMapping("/email")
    String userEmail(@RequestParam("id") UUID id) {
        return userService.findUserById(id)
                .map(User::getEmail)
                .orElseThrow(
                        () -> new UserNotFoundException("Пользователь с id %s не найден".formatted(id)));
    }

    @GetMapping("/information")
    UserResponseDto userInformation(@RequestParam("id") UUID id) {
        return userService.getUserById(id);
    }


    @GetMapping("/user-exists")
    Boolean userExists(@RequestParam("email") String email) {
        Optional<User> user = userService.findUserByEmail(email);

        return user.isPresent();
    }


}
