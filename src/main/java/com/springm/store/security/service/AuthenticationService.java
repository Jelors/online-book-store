package com.springm.store.security.service;

import com.springm.store.dto.user.UserLoginRequestDto;
import com.springm.store.dto.user.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto loginRequestDto);
}
