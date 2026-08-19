package com.bgramma.storeorderapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Order(int tableNumber, int quantity, Menu menu) {
        this.tableNumber    = tableNumber;
        this.quantity       = quantity;
        this.menu           = menu;
    }
}
