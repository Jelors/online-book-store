package com.springm.store.service.impl;

import com.springm.store.dto.shoppingCart.AddShoppingCartRequestDto;
import com.springm.store.dto.shoppingCart.ShoppingCartDto;
import com.springm.store.dto.shoppingCart.UpdateShoppingCartRequestDto;
import com.springm.store.mapper.ShoppingCartMapper;
import com.springm.store.repository.user.UserRepository;
import com.springm.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;
    private final ShoppingCartMapper cartMapper;


    @Override
    public ShoppingCartDto addItem(AddShoppingCartRequestDto requestDto) {
        return null;
    }

    @Override
    public ShoppingCartDto updateCart(Long id, UpdateShoppingCartRequestDto requestDto) {
        return null;
    }

    @Override
    public void removeItem(Long id) {

    }
}
