package com.springm.store.dto.shoppingCart;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddShoppingCartRequestDto {
    @NotBlank
    private long bookId;

    @NotBlank
    private int quantity;
}
