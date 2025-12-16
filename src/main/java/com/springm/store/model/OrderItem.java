package com.springm.store.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orderItems")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Order order;

    @Column(nullable = false)
    private Book book;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;
}
