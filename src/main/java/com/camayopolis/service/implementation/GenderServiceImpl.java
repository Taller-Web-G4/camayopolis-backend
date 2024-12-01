package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.GenderEntity;
import com.camayopolis.persistence.repository.IGenderRepository;
import com.camayopolis.presentation.dto.GenderDto;
import com.camayopolis.service.interfaces.IGenderService;
import com.camayopolis.util.mapper.IGenderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderServiceImpl implements IGenderService {
    private final IGenderRepository genderRepository;
    private final IGenderMapper genderMapper;

    public GenderServiceImpl(IGenderRepository genderRepository, IGenderMapper genderMapper) {
        this.genderRepository = genderRepository;
        this.genderMapper = genderMapper;
    }

    @Override
    public List<GenderDto> getAllGenders() {
        List<GenderEntity> genderEntities = genderRepository.findAll();
        return genderMapper.toDto(genderEntities);
    }

    @Override
    public Optional<GenderDto> getGenderById(Integer id) {
        Optional<GenderEntity> genderEntity = genderRepository.findById(id);
        return genderEntity.map(genderMapper::toDto);
    }

    @Override
    public boolean existsById(Integer id) {
        return genderRepository.existsById(id);
    }

    @Override
    public Optional<GenderDto> createGender(GenderDto genderDto) {
        GenderEntity genderEntity = genderMapper.toEntity(genderDto);
        return Optional.of(genderMapper.toDto(genderRepository.save(genderEntity)));
    }

    @Override
    public Optional<GenderDto> updateGender(Integer id, GenderDto genderDto) {
        if (!genderRepository.existsById(id)) {
            return Optional.empty();
        }

        GenderEntity genderEntity = genderMapper.toEntity(genderDto);
        genderEntity.setId(id);

        return Optional.of(genderMapper.toDto(genderRepository.save(genderEntity)));
    }

    @Override
    public void deleteGender(Integer id) {
        genderRepository.deleteById(id);
    }
}
