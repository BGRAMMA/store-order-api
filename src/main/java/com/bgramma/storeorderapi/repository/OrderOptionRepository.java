package com.bgramma.storeorderapi.repository;

import com.bgramma.storeorderapi.entity.OrderOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOptionRepository extends JpaRepository<OrderOption, Long> {
}
