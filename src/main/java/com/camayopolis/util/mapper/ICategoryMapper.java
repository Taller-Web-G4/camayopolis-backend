package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.CategoryEntity;
import com.camayopolis.presentation.dto.CategoryDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICategoryMapper {
    CategoryEntity toEntity(CategoryDto categoryDto);

    CategoryDto toDto(CategoryEntity categoryEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryEntity partialUpdate(CategoryDto categoryDto, @MappingTarget CategoryEntity categoryEntity);

    List<CategoryEntity> toEntity(List<CategoryDto> categoryDto);

    List<CategoryDto> toDto(List<CategoryEntity> categoryEntity);
}