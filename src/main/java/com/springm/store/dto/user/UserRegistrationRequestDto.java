package com.springm.store.dto.user;

import com.springm.store.validation.FieldMatch;
import com.springm.store.validation.user.Email;
import com.springm.store.validation.user.Name;
import com.springm.store.validation.user.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FieldMatch.List({
        @FieldMatch(
                field = "password",
                fieldMatch = "repeatPassword",
                message = "Password do not match!"
        )
})
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

    @NotBlank
    @Name
    private String firstName;

    @NotBlank
    @Name
    private String lastName;

    @NotBlank
    private String shippingAddress;
}
