package org.mrshoffen.tasktracker.user.profile.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationSuccessfulEvent;
import org.mrshoffen.tasktracker.commons.web.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.exception.UserNotFoundException;
import org.mrshoffen.tasktracker.user.profile.model.dto.ProfileEditDto;
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

    public UserResponseDto updateUserProfileInformation(UUID userId, ProfileEditDto editDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Пользователь с id %s не найден".formatted(userId.toString()))
                );

        user.setCountry(editDto.country());
        user.setRegion(editDto.region());
        user.setFirstName(editDto.firstName());
        user.setLastName(editDto.lastName());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void updateUserPassword(UUID userId, String newHashedPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Пользователь с id %s не найден".formatted(userId.toString()))
                );

        user.setHashedPassword(newHashedPassword);
        userRepository.save(user);
    }

    public UserResponseDto updateUserAvatar(UUID userId, String avatarUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Пользователь с id %s не найден".formatted(userId.toString()))
                );

        user.setAvatarUrl(avatarUrl);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void updateUserEmail(UUID userId, String newEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Пользователь с id %s не найден".formatted(userId.toString()))
                );

        user.setEmail(newEmail);
        userRepository.save(user);
    }
}
