package com.bgramma.storeorderapi.service;

import com.bgramma.storeorderapi.dto.OrderInputDto;
import com.bgramma.storeorderapi.entity.Menu;
import com.bgramma.storeorderapi.entity.Order;
import com.bgramma.storeorderapi.repository.MenuRepository;
import com.bgramma.storeorderapi.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public Order inputOrder(OrderInputDto inputDto) {
        Menu menu = menuRepository.findById(inputDto.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴 번호 입니다. : " + inputDto.getMenuId()));

        Order order = new Order(inputDto.getTableNumber(), inputDto.getQuantity(), menu);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByTableNumber(int tableNumber) {
        return orderRepository.findByTableNumber(tableNumber);
    }
}
