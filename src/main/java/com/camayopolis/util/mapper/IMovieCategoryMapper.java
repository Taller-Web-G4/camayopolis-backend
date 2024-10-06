package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.MovieCategoryEntity;
import com.camayopolis.presentation.dto.MovieCategoryDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IMovieCategoryMapper {
    @Mapping(source = "catCatNombre", target = "cat.catNombre")
    @Mapping(source = "catId", target = "cat.id")
    @Mapping(source = "pelPelTitulo", target = "pel.pelTitulo")
    @Mapping(source = "pelId", target = "pel.id")
    MovieCategoryEntity toEntity(MovieCategoryDto movieCategoryDto);

    @InheritInverseConfiguration(name = "toEntity")
    MovieCategoryDto toDto(MovieCategoryEntity movieCategoryEntity);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MovieCategoryEntity partialUpdate(MovieCategoryDto movieCategoryDto, @MappingTarget MovieCategoryEntity movieCategoryEntity);
}