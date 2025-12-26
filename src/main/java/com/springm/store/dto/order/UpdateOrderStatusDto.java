package com.springm.store.dto.order;

import com.springm.store.model.Order;
import com.springm.store.validation.order.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusDto {
    @Status(enumClass = Order.Status.class)
    private Order.Status status;
}
