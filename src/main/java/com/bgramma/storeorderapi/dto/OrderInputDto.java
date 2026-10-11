package com.bgramma.storeorderapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  주문 요청 데이터를 전달받는 DTO
 */
@Getter
@NoArgsConstructor
public class OrderInputDto {
    private Long menuId;
    private int tableNumber;
    private int quantity;
    private List<OrderOptionInputDto> options;
}
