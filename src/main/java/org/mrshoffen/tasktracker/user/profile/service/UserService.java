package org.mrshoffen.tasktracker.user.profile.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationAttemptEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationSuccessfulEvent;
import org.mrshoffen.tasktracker.user.profile.exception.UserNotFoundException;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.model.entity.User;
import org.mrshoffen.tasktracker.user.profile.repository.UserRepository;
import org.mrshoffen.tasktracker.user.profile.util.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public void createUser(RegistrationSuccessfulEvent event) {
        User userForSave = userMapper.userFromEvent(event);
        userRepository.save(userForSave);
    }

    public UserResponseDto getUserById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(
                        () -> new UserNotFoundException("Пользователь с id %s не найден".formatted(id.toString()))
                );
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository
                .findByEmailIgnoreCase(email);
    }

    public Optional<User> findUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
        log.info("User deleted: {}", id);
    }


}
