package com.camayopolis.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.camayopolis.persistence.entity.ComboEntity}
 */
public record ComboDto(Integer id, @NotNull @Size(max = 100) String name, String description, @NotNull BigDecimal price,
                       @Size(max = 255) String imageUrl) implements Serializable {
}