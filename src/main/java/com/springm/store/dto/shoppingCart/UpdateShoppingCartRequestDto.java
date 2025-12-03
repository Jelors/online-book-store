package com.springm.store.dto.shoppingCart;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateShoppingCartRequestDto {
    @NotBlank
    private int quantity;
}
