package com.bgramma.storeorderapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MenuInputDto {
    private String name;
    private int price;
    private List<MenuOptionInputDto> options;
}
