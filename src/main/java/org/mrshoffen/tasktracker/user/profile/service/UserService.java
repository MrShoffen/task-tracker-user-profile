package org.mrshoffen.tasktracker.user.profile.service;

import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.user.profile.exception.UserAlreadyExistsException;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserCreateDto;
import org.mrshoffen.tasktracker.user.profile.model.dto.UserResponseDto;
import org.mrshoffen.tasktracker.user.profile.model.entity.User;
import org.mrshoffen.tasktracker.user.profile.repositroy.UserRepository;
import org.mrshoffen.tasktracker.user.profile.util.mapper.UserMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        User userForSave = userMapper.toEntity(userCreateDto);

        try {
            userRepository.save(userForSave);
        } catch (DataIntegrityViolationException ex) {
            throw new UserAlreadyExistsException(
                    "Пользователь с почтой %s уже существует!"
                            .formatted(userCreateDto.email()),
                    ex);
        }


        return userMapper.toDto(userForSave);
    }


}
