package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.cartItem.CartItemDto;
import com.springm.store.dto.cartItem.CartRequestDto;
import com.springm.store.dto.cartItem.CartResponseDto;
import com.springm.store.model.CartItem;
import com.springm.store.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ShoppingCartMapper {
    CartItemDto toDto(CartItem cartItem);

    CartItem toModel(CartRequestDto cartRequestDto);

    @Mapping(source = "userId", target = "user")
    CartResponseDto toResponse(CartRequestDto cartRequestDto, User user);
}
