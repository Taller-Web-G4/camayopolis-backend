package com.camayopolis.presentation.dto;

import java.io.Serializable;

public record MovieCategoryDto(Integer pelId, String pelPelTitulo, Integer catId,
                               String catCatNombre) implements Serializable {
}