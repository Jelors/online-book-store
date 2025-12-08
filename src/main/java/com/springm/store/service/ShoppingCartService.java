package com.springm.store.service;

import com.springm.store.dto.cart.AddShoppingCartRequestDto;
import com.springm.store.dto.cart.ShoppingCartDto;
import com.springm.store.dto.cart.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto getCart();

    ShoppingCartResponseDto addItem(AddShoppingCartRequestDto requestDto);

    ShoppingCartDto updateCart(Long id, AddShoppingCartRequestDto requestDto);

    void removeItem(Long id);
}
