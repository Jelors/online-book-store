package com.springm.store.dto.user;

import com.springm.store.validation.user.Email;
import com.springm.store.validation.user.Name;
import com.springm.store.validation.user.Password;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDto {
    @Email
    private String email;
    @Password
    private String password;
    @Name
    private String firstName;
    @Name
    private String lastName;
    private String shippingAddress;

}
