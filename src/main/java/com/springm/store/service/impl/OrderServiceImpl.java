package com.springm.store.service.impl;

import com.springm.store.dto.order.OrderResponseDto;
import com.springm.store.exception.EntityNotFoundException;
import com.springm.store.mapper.CartItemMapper;
import com.springm.store.mapper.OrderItemMapper;
import com.springm.store.mapper.OrderMapper;
import com.springm.store.model.Order;
import com.springm.store.model.OrderItem;
import com.springm.store.model.ShoppingCart;
import com.springm.store.model.User;
import com.springm.store.repository.cart.ShoppingCartRepository;
import com.springm.store.repository.order.OrderRepository;
import com.springm.store.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final CartItemMapper cartItemMapper;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public OrderResponseDto placeOrder(String shippingAddress) {
        User user = userDetailsService.getCurrentUser();
        ShoppingCart cart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException(user.getUsername() + " cart not found!"));

        if (cart.getCartItems().isEmpty()) {
            throw new IllegalArgumentException("Order can't be empty!");
        }

        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(shippingAddress);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.Status.ORDER_PLACED);

        Set<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItemMapper::toDto)
                .map(orderItemMapper::toDtoFromCart)
                .map(orderItemMapper::toModel)
                .peek(item -> item.setOrder(order))
                .collect(Collectors.toSet());
        order.setOrderItems(orderItems);

        cart.clear();
        shoppingCartRepository.save(cart);

        BigDecimal totalPrice = orderItems.stream()
                .map(item -> {
                    BigDecimal price = item.getPrice() != null ? item.getPrice() : BigDecimal.ZERO;
                    return price.multiply(BigDecimal.valueOf(item.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(totalPrice);
        orderRepository.save(order);

        return orderMapper.toResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> receiveOrderHistory() {
        User user = userDetailsService.getCurrentUser();
        return orderRepository.findByUser(user).stream()
                .map(orderMapper::toResponseDto)
                .toList();
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, Order.Status orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Order with id " + orderId + " not found!"
                ));
        order.setStatus(orderStatus);
        return orderMapper.toResponseDto(orderRepository.save(order));
    }
}
