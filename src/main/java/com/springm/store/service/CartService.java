package com.springm.store.service;

import com.springm.store.dto.shoppingCart.AddShoppingCartRequestDto;
import com.springm.store.dto.shoppingCart.ShoppingCartDto;

public interface CartService {
    ShoppingCartDto addItem(AddShoppingCartRequestDto requestDto);

    ShoppingCartDto updateCart(Long id, AddShoppingCartRequestDto requestDto);

    void removeItem(Long id);
}
