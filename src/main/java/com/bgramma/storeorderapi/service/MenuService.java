package com.bgramma.storeorderapi.service;

import com.bgramma.storeorderapi.entity.Menu;
import com.bgramma.storeorderapi.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu saveMenu(String name, int price) {
        Menu menu = new Menu(name, price);
        return menuRepository.save(menu);
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }
}
