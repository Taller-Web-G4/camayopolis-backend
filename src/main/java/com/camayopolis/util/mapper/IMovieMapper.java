package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.MovieEntity;
import com.camayopolis.presentation.dto.MovieDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface IMovieMapper {
    MovieEntity toEntity(MovieDto movieDto);

    MovieDto toDto(MovieEntity movieEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)MovieEntity partialUpdate(MovieDto movieDto, @MappingTarget MovieEntity movieEntity);

    List<MovieEntity> toEntity(List<MovieDto> movieDto);

    List<MovieDto> toDto(List<MovieEntity> movieEntity);
}