package com.springm.store.dto.cartItem;

import com.springm.store.model.User;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDto {
    private long id;
    private User user;
    private Set<CartItemDto> cartItems;
}
