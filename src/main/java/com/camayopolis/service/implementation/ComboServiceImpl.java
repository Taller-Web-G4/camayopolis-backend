package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.ComboEntity;
import com.camayopolis.persistence.repository.IComboRepository;
import com.camayopolis.presentation.dto.ComboDto;
import com.camayopolis.service.interfaces.IComboService;
import com.camayopolis.util.mapper.IComboMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComboServiceImpl implements IComboService {
    private final IComboRepository comboRepository;
    private final IComboMapper comboMapper;

    public ComboServiceImpl(IComboRepository comboRepository, IComboMapper comboMapper) {
        this.comboRepository = comboRepository;
        this.comboMapper = comboMapper;
    }

    @Override
    public List<ComboDto> getAllCombos() {
        List<ComboEntity> comboEntities = this.comboRepository.findAll();
        return comboMapper.toDto(comboEntities);
    }

    @Override
    public Optional<ComboDto> getComboById(Integer id) {
        if (!this.existsById(id)) {
            return Optional.empty();
        }
        Optional<ComboEntity> comboEntity = this.comboRepository.findById(id);
        return comboEntity.map(comboMapper::toDto);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.comboRepository.existsById(id);
    }

    @Override
    public Optional<ComboDto> createCombo(ComboDto comboDto) {
        ComboEntity comboEntity = this.comboMapper.toEntity(comboDto);
        ComboEntity savedEntity = this.comboRepository.save(comboEntity);
        return Optional.of(this.comboMapper.toDto(savedEntity));
    }

    @Override
    public Optional<ComboDto> updateCombo(Integer id, ComboDto comboDto) {
        if (!comboRepository.existsById(id)) {
            return Optional.empty();
        }
        ComboEntity combo = comboMapper.toEntity(comboDto);
        combo.setId(id);
        return Optional.of(comboMapper.toDto(comboRepository.save(combo)));
    }

    @Override
    public void deleteCombo(Integer id) {
        this.comboRepository.deleteById(id);
    }
}
