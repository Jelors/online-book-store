package com.springm.store.service;

import com.springm.store.dto.shoppingCart.AddShoppingCartRequestDto;
import com.springm.store.dto.shoppingCart.ShoppingCartDto;
import com.springm.store.dto.shoppingCart.UpdateShoppingCartRequestDto;

public interface CartService {
    ShoppingCartDto addItem(AddShoppingCartRequestDto requestDto);

    ShoppingCartDto updateCart(Long id, UpdateShoppingCartRequestDto requestDto);

    void removeItem(Long id);
}
