package com.camayopolis.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.camayopolis.persistence.entity.GenderEntity}
 */
public record GenderDto(Integer id, @NotNull @Size(max = 50) String name) implements Serializable {
}