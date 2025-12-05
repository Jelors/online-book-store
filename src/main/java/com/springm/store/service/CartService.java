package com.springm.store.service;

import com.springm.store.dto.cartItem.CartRequestDto;
import com.springm.store.dto.cartItem.CartResponseDto;

public interface CartService {
    CartResponseDto createCart(CartRequestDto cartRequestDto);
}
