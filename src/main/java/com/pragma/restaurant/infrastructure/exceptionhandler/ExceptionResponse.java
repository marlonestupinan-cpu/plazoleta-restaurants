package com.pragma.restaurant.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No se encontraron datos para la petición"),
    NOT_OWNER("El usuario no es un propietario"),
    USER_NOT_FOUND("Usuario no encontrado"),
    CATEGORY_NOT_FOUND("Categoria no encontrada"),
    RESTAURANT_NOT_FOUND("Restaurante no encontrado");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}