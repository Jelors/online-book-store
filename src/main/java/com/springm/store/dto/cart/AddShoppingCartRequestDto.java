package com.springm.store.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddShoppingCartRequestDto {
    private long bookId;
    private int quantity;
}
