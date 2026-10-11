package com.bgramma.storeorderapi.service;

import com.bgramma.storeorderapi.dto.OrderDto;
import com.bgramma.storeorderapi.dto.OrderInputDto;
import com.bgramma.storeorderapi.dto.OrderOptionDto;
import com.bgramma.storeorderapi.dto.OrderOptionInputDto;
import com.bgramma.storeorderapi.entity.Menu;
import com.bgramma.storeorderapi.entity.MenuOption;
import com.bgramma.storeorderapi.entity.Order;
import com.bgramma.storeorderapi.entity.OrderOption;
import com.bgramma.storeorderapi.repository.MenuOptionRepository;
import com.bgramma.storeorderapi.repository.MenuRepository;
import com.bgramma.storeorderapi.repository.OrderOptionRepository;
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
    private final OrderOptionRepository orderOptionRepository;
    private final MenuOptionRepository menuOptionRepository;

    @Transactional
    public List<OrderDto> inputOrder(List<OrderInputDto> requestOrder) {
        List<OrderDto> orderList = new ArrayList<>();

        for ( OrderInputDto dto : requestOrder ) {
            Menu menu = menuRepository.findById(dto.getMenuId()).orElseThrow(()
                    -> new IllegalArgumentException("존재하지 않는 메뉴 입니다. : " + dto.getMenuId()));

            Order order = new Order(dto.getTableNumber(), dto.getQuantity(), menu.getName(), menu.getPrice());
            Order savedOrder = orderRepository.save(order);

            List<OrderOptionDto> orderOptionDtoList = new ArrayList<>();

            if ( dto.getOptions() != null ) {
                for ( OrderOptionInputDto optionDto : dto.getOptions() ) {

                    MenuOption menuOption = menuOptionRepository.findById(optionDto.getOptionId()).orElseThrow(()
                            -> new IllegalArgumentException("존재하지 않는 옵션 입니다. : " + optionDto.getOptionId()));

                    OrderOption orderOption = new OrderOption(menuOption.getName(), menuOption.getPrice(), savedOrder);
                    orderOptionRepository.save(orderOption);

                    OrderOptionDto orderOptionDto =
                            new OrderOptionDto(orderOption.getId(), orderOption.getName(), orderOption.getPrice());

                    orderOptionDtoList.add(orderOptionDto);
                }

            }

            OrderDto orderDto
                    = new OrderDto(order.getId(), order.getTableNumber(), order.getQuantity(), order.getName(), order.getPrice(), orderOptionDtoList);

            orderList.add(orderDto);
        }

        return orderList;

    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByTableNumber(int tableNumber) {
        return orderRepository.findByTableNumber(tableNumber);
    }
}
