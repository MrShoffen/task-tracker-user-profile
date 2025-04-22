package org.mrshoffen.tasktracker.user.profile.http.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.commons.kafka.event.UserCreatedEvent;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserCreateDto;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserProfileController {


    private final UserService userService;

    @GetMapping("/status")
    String check(){
        return "OK";
    }

    @PostMapping
    ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto userCreateDto){
        UserResponseDto createdUser = userService.createUser(userCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
