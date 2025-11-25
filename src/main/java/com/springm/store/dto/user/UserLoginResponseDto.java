package com.springm.store.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponseDto {
    private String token;

    public UserLoginResponseDto() {
    }

    public UserLoginResponseDto(String token) {
        this.token = token;
    }

}
