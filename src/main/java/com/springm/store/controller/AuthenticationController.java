package com.springm.store.controller;

import com.springm.store.dto.user.UserLoginRequestDto;
import com.springm.store.dto.user.UserLoginResponseDto;
import com.springm.store.dto.user.UserRegistrationRequestDto;
import com.springm.store.dto.user.UserResponseDto;
import com.springm.store.security.service.AuthenticationService;
import com.springm.store.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth manager", description = "Endpoints for authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Log4j2
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    @Operation(summary = "Register a new user", description = "Register a new user")
    public ResponseEntity<UserResponseDto> registerUser(
            @RequestBody @Valid UserRegistrationRequestDto userRegistrationRequestDto) {
        log.info("User was registered;");
        return new ResponseEntity<UserResponseDto>(
                userService.register(userRegistrationRequestDto),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Auth user", description = "Authenticate user by email and password")
    public ResponseEntity<UserLoginResponseDto> login(
            @RequestBody UserLoginRequestDto userLoginRequestDto) {
        return new ResponseEntity<UserLoginResponseDto>(
                authenticationService.authenticate(userLoginRequestDto),
                HttpStatus.OK);
    }

}
