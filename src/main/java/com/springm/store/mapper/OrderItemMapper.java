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
    OrderItemDto toOrderItemDto(CartItemDto cartItemDto);

    OrderItem toOrderItemModel(OrderItemDto orderItemDto);
}
