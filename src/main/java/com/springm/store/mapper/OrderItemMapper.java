package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.cart.item.CartItemDto;
import com.springm.store.dto.order.item.OrderItemDto;
import com.springm.store.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItemDto toDtoFromCart(CartItemDto cartItemDto);

    @Mapping(source = "price", target = "price")
    @Mapping(source = "bookId", target = "book.id")
    OrderItem toModel(OrderItemDto orderItemDto);

    OrderItemDto toDto(OrderItem orderItem);
}
