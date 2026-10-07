package com.bgramma.storeorderapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuOptionDto {
    private Long id;
    private String name;
    private int price;
}
