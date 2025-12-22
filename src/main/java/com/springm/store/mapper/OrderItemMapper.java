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
    @Mapping(source = "bookId", target = "bookId")
    @Mapping(source = "bookTitle", target = "bookTitle")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    OrderItemDto toDtoFromCart(CartItemDto cartItemDto);

    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "bookTitle", target = "book.title")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "quantity", target = "quantity")
    OrderItem toModel(OrderItemDto orderItemDto);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    OrderItemDto toDto(OrderItem orderItem);

}
