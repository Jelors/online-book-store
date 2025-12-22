package com.springm.store.service.impl;

import com.springm.store.dto.order.item.OrderItemDto;
import com.springm.store.exception.EntityNotFoundException;
import com.springm.store.mapper.OrderItemMapper;
import com.springm.store.repository.order.item.OrderItemRepository;
import com.springm.store.service.OrderItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemDto> getItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId)
                .stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemDto getItemByOrderIdAndItemId(Long orderId, Long itemId) {
        return orderItemRepository.findByOrderIdAndId(orderId, itemId)
                .map(orderItemMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));
    }

}
