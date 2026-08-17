package com.bgramma.storeorderapi.service;

import com.bgramma.storeorderapi.dto.MenuInputDto;
import com.bgramma.storeorderapi.entity.Menu;
import com.bgramma.storeorderapi.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu inputMenu(MenuInputDto requestDto) {
        Menu menu = new Menu(requestDto.getName(), requestDto.getPrice());
        return menuRepository.save(menu);
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenu(Long id) {
        return menuRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 메뉴 입니다. Menu id : " + id)
        );
    }

    @Transactional
    public Menu updateMenu(Long id, MenuInputDto inputDto) {
        Menu menu = getMenu(id);
        menu.update(inputDto.getName(), inputDto.getPrice());
        return menu;
    }

    public void deleteMenu(Long id) {
        Menu menu = getMenu(id);
        menuRepository.delete(menu);
    }
}
