package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.ComboEntity;
import com.camayopolis.presentation.dto.ComboDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IComboMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "cmbNombre")
    @Mapping(source = "description", target = "cmbDescripcion")
    @Mapping(source = "price", target = "cmbPrecio")
    @Mapping(source = "imageUrl", target = "cmbImagenUrl")
    ComboEntity toEntity(ComboDto comboDto);

    @InheritInverseConfiguration(name = "toEntity")
    ComboDto toDto(ComboEntity comboEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ComboEntity partialUpdate(ComboDto comboDto, @MappingTarget ComboEntity comboEntity);

    List<ComboEntity> toEntity(List<ComboDto> comboDto);
    List<ComboDto> toDto(List<ComboEntity> comboEntity);
}