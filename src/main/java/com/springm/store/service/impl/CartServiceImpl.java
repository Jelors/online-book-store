package com.springm.store.service.impl;

import com.springm.store.dto.cartItem.CartRequestDto;
import com.springm.store.dto.cartItem.CartResponseDto;
import com.springm.store.mapper.ShoppingCartMapper;
import com.springm.store.model.User;
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
    public CartResponseDto createCart(CartRequestDto cartRequestDto) {
        User user = userRepository.findById(cartRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        return cartMapper.toResponse(cartRequestDto, user);
    }
}
