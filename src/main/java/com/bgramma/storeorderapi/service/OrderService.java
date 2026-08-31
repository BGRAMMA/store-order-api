package com.bgramma.storeorderapi.service;

import com.bgramma.storeorderapi.dto.OrderInputDto;
import com.bgramma.storeorderapi.entity.Menu;
import com.bgramma.storeorderapi.entity.Order;
import com.bgramma.storeorderapi.repository.MenuRepository;
import com.bgramma.storeorderapi.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public List<Order> inputOrder(List<OrderInputDto> inputDtos) {
        List<Order> orders = new ArrayList<>();

        for (OrderInputDto dto : inputDtos) {
            Menu menu = menuRepository.findById(dto.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴입니다. : " + dto.getMenuId()));

            Order order = new Order(dto.getTableNumber(), dto.getQuantity(), menu);

            orders.add(order);
        }

        return orderRepository.saveAll(orders);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByTableNumber(int tableNumber) {
        return orderRepository.findByTableNumber(tableNumber);
    }
}
