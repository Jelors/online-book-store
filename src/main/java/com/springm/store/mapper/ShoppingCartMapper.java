package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.cartItem.CartItemDto;
import com.springm.store.dto.cartItem.CartRequestDto;
import com.springm.store.dto.cartItem.CartResponseDto;
import com.springm.store.model.CartItem;
import com.springm.store.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {
    CartItemDto toDto(CartItem cartItem);

    CartItem toModel(CartRequestDto cartRequestDto);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user", target = "user")
    CartResponseDto toResponse(CartRequestDto cartRequestDto, User user);
}
