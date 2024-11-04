package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.ComboItemEntity;
import com.camayopolis.presentation.dto.ComboItemRequestDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IComboItemRequestMapper {
    @Mapping(source = "quantity", target = "cmbiCantidad")
    @Mapping(source = "productId", target = "prd.id")
    @Mapping(source = "comboId", target = "cmb.id")
    ComboItemEntity toEntity(ComboItemRequestDto comboItemRequestDto);

    @InheritInverseConfiguration(name = "toEntity")
    ComboItemRequestDto toDto(ComboItemEntity comboItemEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ComboItemEntity partialUpdate(ComboItemRequestDto comboItemRequestDto, @MappingTarget ComboItemEntity comboItemEntity);

    List<ComboItemEntity> toEntity(List<ComboItemRequestDto> comboItemRequestDtos);
    List<ComboItemRequestDto> toDto(List<ComboItemEntity> comboItemEntities);
}