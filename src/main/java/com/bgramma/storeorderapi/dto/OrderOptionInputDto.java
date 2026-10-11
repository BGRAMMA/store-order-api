package com.bgramma.storeorderapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderOptionInputDto {
    private Long orderId;
    private String name;
    private int price;
}
