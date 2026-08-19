package com.bgramma.storeorderapi.repository;

import com.bgramma.storeorderapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByTableNumber(int tableNumber);
}
