package com.springm.store.dto.cartItem;

import com.springm.store.model.Book;
import com.springm.store.model.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private long id;
    private ShoppingCart shoppingCart;
    private Book book;
    private int quantity;
}
