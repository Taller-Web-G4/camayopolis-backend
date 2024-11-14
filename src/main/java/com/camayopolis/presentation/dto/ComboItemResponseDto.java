package com.camayopolis.presentation.dto;

import java.io.Serializable;

public record ComboItemResponseDto(Integer id, Integer quantity, ProductDto product, ComboDto combo) implements Serializable {
}