package org.mrshoffen.tasktracker.user.profile.http.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.user.profile.exception.UserNotFoundException;
import org.mrshoffen.tasktracker.user.profile.model.entity.User;
import org.mrshoffen.tasktracker.user.profile.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/*
 * Эндпоинты только для межсервисного взаимодействия
 * */
@RequiredArgsConstructor
@Slf4j
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


    @GetMapping("/user-exists")
    Boolean userExists(@RequestParam("email") String email) {
        Optional<User> user = userService.findUserByEmail(email);

        return user.isPresent();
    }


}
