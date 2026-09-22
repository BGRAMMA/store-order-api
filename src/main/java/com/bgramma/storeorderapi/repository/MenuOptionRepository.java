package com.bgramma.storeorderapi.repository;

import com.bgramma.storeorderapi.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
