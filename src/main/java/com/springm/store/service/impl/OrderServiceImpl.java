package com.springm.store.service.impl;

import com.springm.store.dto.order.OrderResponseDto;
import com.springm.store.model.Order;
import com.springm.store.service.OrderService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Override
    public boolean placeOrder(String shippingAddress) {
        return false;
    }

    @Override
    public List<OrderResponseDto> receiveOrderHistory() {
        return List.of();
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, Order.Status orderStatus) {
        return null;
    }
}
