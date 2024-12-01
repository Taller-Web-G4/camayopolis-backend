package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.GenderEntity;
import com.camayopolis.presentation.dto.GenderDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IGenderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "genNombre")
    GenderEntity toEntity(GenderDto genderDto);

    @InheritInverseConfiguration(name = "toEntity")
    GenderDto toDto(GenderEntity genderEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GenderEntity partialUpdate(GenderDto genderDto, @MappingTarget GenderEntity genderEntity);

    List<GenderEntity> toEntity(List<GenderDto> genderDto);
    List<GenderDto> toDto(List<GenderEntity> genderEntity);
}