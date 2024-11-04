package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.ProductEntity;
import com.camayopolis.presentation.dto.ProductDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface IProductMapper {
    @Mapping(target="id", ignore=true)
    @Mapping(source="name", target="prdNombre")
    @Mapping(source="description", target="prdDescripcion")
    @Mapping(source="price", target="prdPrecio")
    ProductEntity toEntity(ProductDto productDto);

    @InheritInverseConfiguration(name = "toEntity")
    ProductDto toDto(ProductEntity productEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)ProductEntity partialUpdate(ProductDto productDto, @MappingTarget ProductEntity productEntity);

    List<ProductEntity> toEntity(List<ProductDto> productDto);
    List<ProductDto> toDto(List<ProductEntity> productEntity);
}