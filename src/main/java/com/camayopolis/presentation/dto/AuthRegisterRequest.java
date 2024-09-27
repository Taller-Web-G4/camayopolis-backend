package com.camayopolis.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequest(
        @NotBlank String email,
        @NotBlank String password) {
}
