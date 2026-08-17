package com.bgramma.storeorderapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menus")
@Getter
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void update(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
