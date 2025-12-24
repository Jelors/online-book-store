package com.springm.store.dto.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddShippingAddressDto {
    @NotBlank
    private String shippingAddress;
}
