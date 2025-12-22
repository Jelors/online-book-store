package com.springm.store.controller;

import com.springm.store.dto.order.item.OrderItemDto;
import com.springm.store.service.OrderItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/{orderId}/items")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<OrderItemDto>> getOrderItems(@PathVariable Long orderId) {
        return new ResponseEntity<List<OrderItemDto>>(
                orderItemService.getItemsByOrderId(orderId),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<OrderItemDto> getOrderItem(@PathVariable Long orderId,
                                                     @PathVariable Long id) {
        return new ResponseEntity<OrderItemDto>(
                orderItemService.getItemByOrderIdAndItemId(orderId, id),
                HttpStatus.OK
        );
    }

}
