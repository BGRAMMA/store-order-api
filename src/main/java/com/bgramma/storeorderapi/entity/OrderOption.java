package com.bgramma.storeorderapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_options")
@Getter
@NoArgsConstructor
public class OrderOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderOption(String name, int price, Order order) {
        this.name  = name;
        this.price = price;
        this.order = order;
    }
}
