package com.bgramma.storeorderapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus")
@Getter
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @OneToMany(mappedBy = "menu")
    private List<MenuOption> options = new ArrayList<>();

    public Menu(String name, int price) {
        this.name   = name;
        this.price  = price;
    }

    public void update(String name, int price) {
        this.name   = name;
        this.price  = price;
    }
}
