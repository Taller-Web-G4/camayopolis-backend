package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.ComboItemRequestDto;
import com.camayopolis.presentation.dto.ComboItemResponseDto;

import java.util.List;
import java.util.Optional;

public interface IComboItemService {
    List<ComboItemResponseDto> getAllComboItems();
    Optional<ComboItemResponseDto> getComboItemById(Integer id);
    boolean existsById(Integer id);
    Optional<ComboItemRequestDto> createComboItem(ComboItemRequestDto comboItemRequestDto);
    Optional<ComboItemRequestDto> updateComboItem(Integer id, ComboItemRequestDto comboItemRequestDto);
    void deleteComboItem(Integer id);
}
