package org.mrshoffen.tasktracker.user.profile.http.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.service.UserService;
import org.springframework.core.env.Environment;
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
@Slf4j
public class UserProfileController {

    private final UserService userService;

    private final Environment env;

    @GetMapping("/status")
    String check(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        return "OK - " + remoteAddr + " " + env.getProperty("local.server.port");
    }



    @GetMapping("/me")//todo move to separate controller
    ResponseEntity<UserResponseDto> me(@RequestHeader(AUTHORIZED_USER_HEADER_NAME) String userId){

        return ResponseEntity.ok(
                userService.findUserById(UUID.fromString(userId))
        );
    }

}
