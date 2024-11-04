package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.ComboDto;

import java.util.List;
import java.util.Optional;

public interface IComboService {
    List<ComboDto> getAllCombos();
    Optional<ComboDto> getComboById(Integer id);
    boolean existsById(Integer id);
    Optional<ComboDto> createCombo(ComboDto comboDTO);
    Optional<ComboDto> updateCombo(Integer id, ComboDto comboDTO);
    void deleteCombo(Integer id);
}
