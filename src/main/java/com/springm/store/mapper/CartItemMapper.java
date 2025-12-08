package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.cart.item.CartItemDto;
import com.springm.store.dto.cart.item.CartRequestDto;
import com.springm.store.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDto toDto(CartItem cartItem);

    CartItem toModel(CartRequestDto cartRequestDto);

}
