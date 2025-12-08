package com.springm.store.dto.cart.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private long id;
    private long bookId;
    private String bookTitle;
    private int quantity;
}
