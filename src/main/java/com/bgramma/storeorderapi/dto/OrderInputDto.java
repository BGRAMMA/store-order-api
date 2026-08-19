package com.bgramma.storeorderapi.dto;

import lombok.Getter;

/**
 * @menuId      : 메뉴 번호
 * @tableNumber : 테이블 번호
 * @quantity    : 메뉴 개수
 */
@Getter
public class OrderInputDto {
    private Long menuId;
    private int tableNumber;
    private int quantity;
}
