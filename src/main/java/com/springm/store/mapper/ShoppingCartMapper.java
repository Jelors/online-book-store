package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.cart.ShoppingCartResponseDto;
import com.springm.store.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "cartItems", target = "cartItems")
    ShoppingCartResponseDto toResponse(ShoppingCart shoppingCart);
}
