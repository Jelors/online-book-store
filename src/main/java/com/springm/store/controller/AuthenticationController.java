package com.springm.store.controller;

import com.springm.store.dto.user.UserRegistrationRequestDto;
import com.springm.store.dto.user.UserResponseDto;
import com.springm.store.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth manager", description = "Endpoints for authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Log4j2
public class AuthenticationController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    @Operation(summary = "Register a new user", description = "Register a new user")
    public UserResponseDto registerUser(
            @RequestBody @Valid UserRegistrationRequestDto userRegistrationRequestDto) {
        log.debug("User was registered.");
        return userService.register(userRegistrationRequestDto);
    }
}
