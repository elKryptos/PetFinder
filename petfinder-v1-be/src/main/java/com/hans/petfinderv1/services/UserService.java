package com.hans.petfinderv1.services;

import com.hans.petfinderv1.Constants;
import com.hans.petfinderv1.exception.DataIntegrityViolationException;
import com.hans.petfinderv1.exception.NotFoundException;
import com.hans.petfinderv1.model.dto.UserDto;
import com.hans.petfinderv1.model.entity.User;
import com.hans.petfinderv1.model.mapper.UserMapper;
import com.hans.petfinderv1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public UserDto getUserById(Long userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Constants.USER_NOT_FOUND.getMessage())));
        return userMapper.toDto(user.get());
    }

    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException(Constants.USER_NOT_FOUND.getMessage()));
    }

    public UserDto create(UserDto userDto) {
        boolean emailExists = userRepository.existsByEmail(userDto.getEmail());
        if (emailExists) {
            throw new DataIntegrityViolationException(Constants.USE_ANOTHER_EMAIL.getMessage());
        }
        User user = userMapper.toEntity(userDto);
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        user.setRegistrationDate(now);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDto update(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(Constants.USER_NOT_FOUND.getMessage()));
        userMapper.updateUser(user, userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    public String delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(Constants.USER_NOT_FOUND.getMessage()));
        userRepository.delete(user);
        return Constants.USER_DELETED.getMessage();
    }

}
