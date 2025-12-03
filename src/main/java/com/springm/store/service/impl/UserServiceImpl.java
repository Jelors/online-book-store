package com.springm.store.service.impl;

import com.springm.store.dto.user.UserRegistrationRequestDto;
import com.springm.store.dto.user.UserResponseDto;
import com.springm.store.exception.RegistrationException;
import com.springm.store.exception.RoleNotFoundException;
import com.springm.store.mapper.UserMapper;
import com.springm.store.model.Role;
import com.springm.store.model.User;
import com.springm.store.repository.role.RoleRepository;
import com.springm.store.repository.user.UserRepository;
import com.springm.store.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {

        if (userRepository.existsByEmail(userRegistrationRequestDto.getEmail())) {
            throw new RegistrationException("Email "
                    + userRegistrationRequestDto.getEmail()
                    + " already registered!");
        }

        Role userRole = roleRepository
                .findByRoleName(Role.RoleName.USER)
                .orElseThrow(() -> new RoleNotFoundException("Role USER not found!"));

        User user = userMapper.toModel(userRegistrationRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(userRole));

        return userMapper.toUserResponse(userRepository.save(user));
    }
}
