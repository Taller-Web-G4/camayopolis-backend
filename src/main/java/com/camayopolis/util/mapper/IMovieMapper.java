package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.MovieEntity;
import com.camayopolis.presentation.dto.MovieDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IMovieMapper {
    @Mapping(source = "isPreSale", target = "pelEsPreventa")
    @Mapping(source = "isNewRelease", target = "pelEsNuevoLanzamiento")
    @Mapping(source = "isComingSoon", target = "pelEsEstrenoProximo")
    @Mapping(source = "isInTheater", target = "pelEnCartelera")
    @Mapping(source = "trailer", target = "pelTrailerUrl")
    @Mapping(source = "posterUrl", target = "pelPosterUrl")
    @Mapping(source = "runtime", target = "pelDuracionMinutos")
    @Mapping(source = "openingDate", target = "pelFechaEstreno")
    @Mapping(source = "synopsis", target = "pelSinopsis")
    @Mapping(source = "title", target = "pelTitulo")
    @Mapping(target = "cat", ignore = true)
    @Mapping(source = "gender.id", target = "gen.id")
    @Mapping(source = "gender.name", target = "gen.genNombre")
    MovieEntity toEntity(MovieDto movieDto);

    @InheritInverseConfiguration(name = "toEntity")
    MovieDto toDto(MovieEntity movieEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)MovieEntity partialUpdate(MovieDto movieDto, @MappingTarget MovieEntity movieEntity);

    List<MovieEntity> toEntity(List<MovieDto> movieDto);

    List<MovieDto> toDto(List<MovieEntity> movieEntity);
}