package com.bgramma.storeorderapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private int tableNumber;
    private int quantity;
    private String name;
    private int price;
    private List<OrderOptionDto> options;
}
