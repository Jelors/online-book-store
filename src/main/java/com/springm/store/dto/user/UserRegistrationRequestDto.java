package com.springm.store.dto.user;

import com.springm.store.validation.user.Email;
import com.springm.store.validation.user.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Password
    private String password;

    @NotBlank
    @Password
    private String repeatPassword;
}
