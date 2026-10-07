package com.bgramma.storeorderapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MenuDto {
    private Long id;
    private String name;
    private int price;
    private List<MenuOptionDto> options;
}
