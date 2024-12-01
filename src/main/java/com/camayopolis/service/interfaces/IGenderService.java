package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.GenderDto;

import java.util.List;
import java.util.Optional;

public interface IGenderService {
    List<GenderDto> getAllGenders();
    Optional<GenderDto> getGenderById(Integer id);
    boolean existsById(Integer id);
    Optional<GenderDto> createGender(GenderDto genderDto);
    Optional<GenderDto> updateGender(Integer id, GenderDto genderDto);
    void deleteGender(Integer id);
}
