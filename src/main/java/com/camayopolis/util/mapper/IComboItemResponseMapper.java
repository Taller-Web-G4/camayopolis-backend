package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.ComboItemEntity;
import com.camayopolis.presentation.dto.ComboItemResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IComboItemResponseMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "quantity", target = "cmbiCantidad")
    @Mapping(source = "product", target = "prd")
    @Mapping(source = "combo", target = "cmb")
    @Mapping(source = "product.id", target = "prd.id")
    @Mapping(source = "product.name", target = "prd.prdNombre")
    @Mapping(source = "product.description", target = "prd.prdDescripcion")
    @Mapping(source = "product.price", target = "prd.prdPrecio")
    @Mapping(source = "combo.id", target = "cmb.id")
    @Mapping(source = "combo.name", target = "cmb.cmbNombre")
    @Mapping(source = "combo.description", target = "cmb.cmbDescripcion")
    @Mapping(source = "combo.price", target = "cmb.cmbPrecio")
    @Mapping(source = "combo.imageUrl", target = "cmb.cmbImagenUrl")
    ComboItemEntity toEntity(ComboItemResponseDto comboItemResponseDto);

    @InheritInverseConfiguration(name = "toEntity")
    ComboItemResponseDto toDto(ComboItemEntity comboItemEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ComboItemEntity partialUpdate(ComboItemResponseDto comboItemResponseDto, @MappingTarget ComboItemEntity comboItemEntity);

    List<ComboItemEntity> toEntity(List<ComboItemResponseDto> comboItemResponseDtos);
    List<ComboItemResponseDto> toDto(List<ComboItemEntity> comboItemEntities);
}