package com.bgramma.storeorderapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderOptionDto {

    private Long id;
    private String name;
    private int price;
}
