package com.bgramma.storeorderapi.controller;

import com.bgramma.storeorderapi.entity.Menu;
import com.bgramma.storeorderapi.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/api/menus")
    public List<Menu> getMenus() {
        return menuService.getAllMenus();
    }

    @PostMapping("/api/menus")
    public Menu craeteMenu() {
        return menuService.saveMenu("아메리카노", 4000);
    }
}
