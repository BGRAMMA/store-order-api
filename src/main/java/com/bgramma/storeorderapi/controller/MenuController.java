package com.bgramma.storeorderapi.controller;

import com.bgramma.storeorderapi.dto.MenuDto;
import com.bgramma.storeorderapi.dto.MenuInputDto;
import com.bgramma.storeorderapi.entity.Menu;
import com.bgramma.storeorderapi.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    /**
     * 전체 메뉴 조회
     * @return List<Menu>
     */
    @GetMapping("/api/menus")
    public List<MenuDto> getMenus() {
        return menuService.getAllMenus();
    }

    /**
     * 메뉴 단건 조회
     * @param id
     * @return
     */
    @GetMapping("/api/menus/{id}")
    public MenuDto getMenu(@PathVariable Long id) {
        return menuService.getMenu(id);
    }

    /**
     * 메뉴 생성
     * @param inputDto
     * @return Menu
     */
    @PostMapping("/api/menus")
    public MenuDto inputMenu(@RequestBody MenuInputDto inputDto) {
        return menuService.inputMenu(inputDto);
    }

    /**
     * 메뉴 수정
     * @param id
     * @param inputDto
     * @return
     */
    @PutMapping("/api/menus/{id}")
    public Menu updateMenu(@PathVariable Long id, @RequestBody MenuInputDto inputDto) {
        return menuService.updateMenu(id, inputDto);
    }

    @DeleteMapping("/api/menus/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return id + "번 메뉴가 성공적으로 삭제되었습니다.";
    }
}
