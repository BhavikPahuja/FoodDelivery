package com.jpa.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_item_id")
    private FoodItem foodItem;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal quantity;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;
}