package com.springm.store.service;

import com.springm.store.dto.user.UserLoginRequestDto;
import com.springm.store.dto.user.UserLoginResponseDto;
import com.springm.store.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserLoginResponseDto authenticate(UserLoginRequestDto loginRequestDto) {
        String token = jwtUtil.generateToken(loginRequestDto.getEmail());
        return null;
    }
}
