package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.cart.item.CartItemDto;
import com.springm.store.dto.order.item.OrderItemDto;
import java.math.BigDecimal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "price", source = "bookPrice")
    OrderItemDto toOrderItemDto(CartItemDto cartItemDto, BigDecimal bookPrice);
}
