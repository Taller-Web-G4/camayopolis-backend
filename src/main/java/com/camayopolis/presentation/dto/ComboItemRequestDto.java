package com.camayopolis.presentation.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record ComboItemRequestDto(Integer id, @NotNull Integer quantity, @NotNull Integer productId, Integer comboId) implements Serializable {
}