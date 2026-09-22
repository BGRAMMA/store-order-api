package com.bgramma.storeorderapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_options")
@Getter
@NoArgsConstructor
public class MenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public MenuOption(String name, int price, Menu menu) {
        this.name = name;
        this.price = price;
        this.menu = menu;
    }
}
