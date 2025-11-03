package com.springm.store.service;

import com.springm.store.dto.user.UserRegistrationRequestDto;
import com.springm.store.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto);
}
