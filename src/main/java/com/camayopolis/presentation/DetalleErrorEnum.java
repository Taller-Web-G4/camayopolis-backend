package com.camayopolis.presentation;

public enum DetalleErrorEnum {

    // funcionales
    FUNCIONAL("F001", "Error funcional general"),
    VALIDACION("F002", "Error de validación de datos"),
    AUTENTICACION("F003", "Error de autenticación"),
    AUTORIZACION("F004", "Error de autorización"),

    // sistema
    SISTEMA("S001", "Error interno del servidor"),
    BASE_DATOS("S002", "Error en la base de datos"),
    SERVICIO_EXTERNO("S003", "Error en servicio externo");

    private final String codigo;
    private final String descripcion;

    DetalleErrorEnum(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
