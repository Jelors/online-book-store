package com.springm.store.service;

import com.springm.store.dto.order.item.OrderItemDto;
import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getItemsByOrderId(Long orderId);

    OrderItemDto getItemByOrderIdAndItemId(Long orderId, Long itemId);
}
