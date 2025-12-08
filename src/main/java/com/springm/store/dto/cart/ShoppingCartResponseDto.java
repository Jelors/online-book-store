package com.springm.store.dto.cart;

import com.springm.store.dto.cart.item.CartItemDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartResponseDto {
    private long id;
    private long userId;
    private List<CartItemDto> cartItems;
}
