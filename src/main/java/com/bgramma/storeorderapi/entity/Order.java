package com.bgramma.storeorderapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tableNumber;

    private int quantity;

    private String name;
    private int price;

    @OneToMany(mappedBy = "order")
    private List<OrderOption> options = new ArrayList<>();

    public Order(int tableNumber, int quantity, String name, int price) {
        this.tableNumber    = tableNumber;
        this.quantity       = quantity;
        this.name           = name;
        this.price          = price;
    }
}
