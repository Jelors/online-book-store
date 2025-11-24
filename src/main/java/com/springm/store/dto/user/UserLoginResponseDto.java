package com.springm.store.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponseDto {
    public UserLoginResponseDto() {
    }

    public UserLoginResponseDto(String token) {
        this.token = token;
    }

    String token;
}
