package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.order.OrderResponseDto;
import com.springm.store.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderResponseDto toResponseDto(Order order);
}
