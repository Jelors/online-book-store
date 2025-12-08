package com.springm.store.dto.cart.item;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDto {
    private long id;
    private long userId;
    private Set<CartItemDto> cartItems;
}
