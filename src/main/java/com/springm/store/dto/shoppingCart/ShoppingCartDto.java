package com.springm.store.dto.shoppingCart;

import com.springm.store.model.CartItem;
import com.springm.store.model.User;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartDto {
    private long id;
    private User user;
    private Set<CartItem> cartItemSet;
}
