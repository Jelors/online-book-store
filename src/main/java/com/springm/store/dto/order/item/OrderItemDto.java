package com.springm.store.dto.order.item;

import com.springm.store.model.Order;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Long id;
    private Order order;
    private Long bookId;
    private String bookTitle;
    private int quantity;
    private BigDecimal price;
}
