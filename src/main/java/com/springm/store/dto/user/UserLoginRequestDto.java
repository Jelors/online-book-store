package com.springm.store.dto.user;

import com.springm.store.validation.user.Email;
import com.springm.store.validation.user.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto {
    @NotBlank
    @Email
    String email;

    @NotBlank
    @Password
    String password;
}
