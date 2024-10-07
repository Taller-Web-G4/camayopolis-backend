package com.camayopolis.presentation.dto;

import java.io.Serializable;

public record MovieCategoryDto(Integer movieId, String movieTitle, Integer categoryId,
                               String categoryName) implements Serializable {
}