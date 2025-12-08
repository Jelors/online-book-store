package com.springm.store.dto.cart;

import com.springm.store.model.CartItem;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartDto {
    private long id;
    private long userId;
    private Set<CartItem> cartItemSet;
}
