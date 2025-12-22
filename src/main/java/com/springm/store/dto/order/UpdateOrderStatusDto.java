package com.springm.store.dto.order;

import com.springm.store.model.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusDto {
    private Order.Status status;
}
