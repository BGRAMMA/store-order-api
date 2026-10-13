package com.bgramma.storeorderapi.controller;

import com.bgramma.storeorderapi.dto.OrderDto;
import com.bgramma.storeorderapi.dto.OrderInputDto;
import com.bgramma.storeorderapi.entity.Order;
import com.bgramma.storeorderapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 전체 주문 조회
     */
    @GetMapping("/api/orders")
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }

    /**
     * 주문 생성
     */
    @PostMapping("/api/orders")
    public List<OrderDto> inputOrder(@RequestBody List<OrderInputDto> inputDtos) {
        return orderService.inputOrder(inputDtos);
    }

    /**
     * 테이블 별 주문 조회
     */
    @GetMapping("/api/orders/{tableNumber}")
    public List<Order> getOrderByTable(@PathVariable int tableNumber) {
        return orderService.getOrderByTableNumber(tableNumber);
    }

}
