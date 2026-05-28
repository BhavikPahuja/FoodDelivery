package com.jpa.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class DeliveryStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_boy_id")
    private User deliveryBoy;

    @Enumerated(EnumType.STRING)
    private DeliveryStatuses status = DeliveryStatuses.ASSIGNED;

    private LocalDateTime updatedAt = LocalDateTime.now();
}