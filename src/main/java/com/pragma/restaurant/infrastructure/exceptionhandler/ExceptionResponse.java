package com.pragma.restaurant.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No se encontraron datos para la petición"),
    NOT_OWNER("El usuario no es un propietario"),
    USER_NOT_FOUND("Usuario no encontrado"),
    CATEGORY_NOT_FOUND("Categoria no encontrada"),
    RESTAURANT_NOT_FOUND("Restaurante no encontrado"),
    DISH_NOT_FOUND("Plato no encontrado"),
    NOT_RESTAURANT_OWNER("El usuario no coincide con el propietario del restaurante"),
    ALREADY_CLIENT_ORDER_ACTIVE("El cliente ya tiene un pedido activo"),
    ORDER_NOT_FOUND("Pedido no encontrado"),
    NOT_RESTAURANT_EMPLOYEE("El usuario no pertenece al restaurante"),
    ORDER_ALREADY_ASSIGNED("El pedido ya se encuentra asignado"),
    ORDER_NOT_ACTIVE("La orden no se encuentra activa");
    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}