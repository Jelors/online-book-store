package com.springm.store.service;

import com.springm.store.dto.order.AddShippingAddressDto;
import com.springm.store.dto.order.OrderResponseDto;
import com.springm.store.dto.order.UpdateOrderStatusDto;
import com.springm.store.dto.order.item.OrderItemDto;
import java.util.List;

public interface OrderService {
    OrderResponseDto placeOrder(AddShippingAddressDto shippingAddressDto);

    List<OrderResponseDto> receiveOrderHistory();

    OrderResponseDto updateOrderStatus(Long orderId, UpdateOrderStatusDto orderStatus);

    List<OrderItemDto> getItemsByOrderId(Long orderId);

    OrderItemDto getItemByOrderIdAndItemId(Long orderId, Long itemId);
}
