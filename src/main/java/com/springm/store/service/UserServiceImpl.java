package com.springm.store.service;

import com.springm.store.dto.user.UserRegistrationRequestDto;
import com.springm.store.dto.user.UserResponseDto;
import com.springm.store.exception.RegistrationException;
import com.springm.store.mapper.UserMapper;
import com.springm.store.model.User;
import com.springm.store.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(userRegistrationRequestDto.getEmail())) {
            throw new RegistrationException("Can't register user!");
        }
        User user = userMapper.toModel(userRegistrationRequestDto);
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
