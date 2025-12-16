package com.springm.store.dto.order.item;

import com.springm.store.model.Book;
import com.springm.store.model.Order;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Long id;
    private Order order;
    private Book book;
    private int quantity;
    private BigDecimal price;
}
