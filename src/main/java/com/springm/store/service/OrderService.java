package com.springm.store.service;

import com.springm.store.dto.order.OrderResponseDto;
import com.springm.store.model.Order;
import java.util.List;

public interface OrderService {
    OrderResponseDto placeOrder(String shippingAddress);

    List<OrderResponseDto> receiveOrderHistory();

    OrderResponseDto updateOrderStatus(Long orderId, Order.Status orderStatus);

}
