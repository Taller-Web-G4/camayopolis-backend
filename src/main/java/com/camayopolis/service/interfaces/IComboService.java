package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.ComboDto;

import java.util.List;
import java.util.Optional;

public interface IComboService {
    List<ComboDto> getAllCombos();
    Optional<ComboDto> getComboById(Integer id);
    boolean existsById(Integer id);
    Optional<ComboDto> createCombo(ComboDto comboDto);
    Optional<ComboDto> updateCombo(Integer id, ComboDto comboDto);
    void deleteCombo(Integer id);
}
