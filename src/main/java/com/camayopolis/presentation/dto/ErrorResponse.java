package com.camayopolis.presentation.dto;

import java.io.Serializable;

public record ErrorResponse(String tipo, String codigo, String mensaje, int status) implements Serializable {

    public static ErrorResponse buildErrorResponse(String tipo, String codigo, String mensaje, int status) {
        return new ErrorResponse(tipo, codigo, mensaje, status);
    }
}
