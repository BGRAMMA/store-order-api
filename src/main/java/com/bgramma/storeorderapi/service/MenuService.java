package com.bgramma.storeorderapi.service;

import com.bgramma.storeorderapi.dto.MenuDto;
import com.bgramma.storeorderapi.dto.MenuInputDto;
import com.bgramma.storeorderapi.dto.MenuOptionDto;
import com.bgramma.storeorderapi.dto.MenuOptionInputDto;
import com.bgramma.storeorderapi.entity.Menu;
import com.bgramma.storeorderapi.entity.MenuOption;
import com.bgramma.storeorderapi.repository.MenuOptionRepository;
import com.bgramma.storeorderapi.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuOptionRepository optionRepository;

    /**
     * 메뉴 등록
     */
    public MenuDto inputMenu(MenuInputDto requestDto) {
        Menu menu = new Menu(requestDto.getName(), requestDto.getPrice());
        menuRepository.save(menu);

        if ( requestDto.getOptions() != null ) {
            for ( MenuOptionInputDto optionDto : requestDto.getOptions() ) {
                MenuOption option = new MenuOption(optionDto.getName(), optionDto.getPrice(), menu);
                optionRepository.save(option);
            }
        }

        return convertToDto(menu);
    }

    /**
     * 메뉴 전체 조회
     */
    public List<MenuDto> getAllMenus() {
        List<Menu> menuList = menuRepository.findAll();
        List<MenuDto> dtoList = new ArrayList<>();

        for ( Menu menu : menuList ) dtoList.add(convertToDto(menu));

        return dtoList;
    }

    /**
     * 메뉴 단건 조회
     */
    public MenuDto getMenu(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 메뉴 입니다. Menu id : " + id));

        return convertToDto(menu);
    }

    /**
     * 메뉴 업데이트
     */
    @Transactional
    public Menu updateMenu(Long id, MenuInputDto inputDto) {
        Menu menu = findMenuEntity(id);
        menu.update(inputDto.getName(), inputDto.getPrice());
        return menu;
    }

    /**
     * 메뉴 삭제
     */
    public void deleteMenu(Long id) {
        Menu menu = findMenuEntity(id);
        menuRepository.delete(menu);
    }

private Menu findMenuEntity(Long id) {
        return menuRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 메뉴 입니다. Menu id : " + id));
    }

    private MenuDto convertToDto(Menu menu) {
        List<MenuOptionDto> optionDtos = new ArrayList<>();

        for ( MenuOption option : menu.getOptions() ) {
            MenuOptionDto optionDto = new MenuOptionDto(option.getId(), option.getName(), option.getPrice());

            optionDtos.add(optionDto);
        }

        return new MenuDto(menu.getId(), menu.getName(), menu.getPrice(), optionDtos);
    }
}
