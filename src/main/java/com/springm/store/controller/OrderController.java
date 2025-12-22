package com.springm.store.controller;

import com.springm.store.dto.order.OrderResponseDto;
import com.springm.store.dto.order.UpdateOrderStatusDto;
import com.springm.store.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Place an order", description = "Place an order")
    public ResponseEntity<OrderResponseDto> placeOrder(
            @RequestBody String shippingAddress
    ) {
        return new ResponseEntity<OrderResponseDto>(
                orderService.placeOrder(shippingAddress),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Receive orders history", description = "Receive orders history")
    public ResponseEntity<List<OrderResponseDto>> getOrderHistory() {
        return new ResponseEntity<List<OrderResponseDto>>(
                orderService.receiveOrderHistory(),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Update order status",
            description = "Updates order status with specified ID"
    )
    public ResponseEntity<OrderResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateOrderStatusDto orderStatusDto
    ) {
        return new ResponseEntity<OrderResponseDto>(
                orderService.updateOrderStatus(id, orderStatusDto),
                HttpStatus.NO_CONTENT
        );
    }

}
