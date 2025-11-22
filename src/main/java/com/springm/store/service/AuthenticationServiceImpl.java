package com.springm.store.service;

import com.springm.store.dto.user.UserLoginRequestDto;
import com.springm.store.dto.user.UserLoginResponseDto;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public UserLoginResponseDto authenticate(UserLoginRequestDto loginRequestDto) {
        return null;
    }
}
