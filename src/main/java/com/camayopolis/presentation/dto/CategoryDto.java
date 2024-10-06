package com.camayopolis.presentation.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record CategoryDto(Integer id, @NotBlank String catNombre) implements Serializable {
}