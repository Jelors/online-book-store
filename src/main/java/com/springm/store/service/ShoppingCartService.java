package com.springm.store.service;

import com.springm.store.dto.cart.AddShoppingCartRequestDto;
import com.springm.store.dto.cart.ShoppingCartResponseDto;
import com.springm.store.model.User;

public interface ShoppingCartService {
    ShoppingCartResponseDto getCart();

    ShoppingCartResponseDto addItem(AddShoppingCartRequestDto requestDto);

    ShoppingCartResponseDto updateCart(Long id, AddShoppingCartRequestDto requestDto);

    void removeItem(Long id);

    void createCartAndSetUser(User user);
}
