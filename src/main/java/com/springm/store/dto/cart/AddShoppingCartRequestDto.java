package com.springm.store.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddShoppingCartRequestDto {
    @NotNull
    private Long bookId;

    @Min(0)
    private int quantity;
}
