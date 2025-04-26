package org.mrshoffen.tasktracker.user.profile.http.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.web.authentication.AuthenticationAttributes;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserCreateDto;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.mrshoffen.tasktracker.commons.web.authentication.AuthenticationAttributes.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {

    private final UserService userService;

    private final Environment env;

    @GetMapping("/status")
    String check(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        return "OK - " + remoteAddr + " " + env.getProperty("local.server.port");
    }

    @GetMapping("/me")
    ResponseEntity<String> me(@RequestHeader(AUTHORIZED_USER_HEADER_NAME) String userId){

        return ResponseEntity.ok(userId);
    }

    @PostMapping
    ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto userCreateDto,
                                           @RequestHeader(value = "X-Forwarded-For", required = false) String userIp){
        UserResponseDto createdUser = userService.createUser(userCreateDto, userIp);
        log.info("New user created: {}", createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


}
