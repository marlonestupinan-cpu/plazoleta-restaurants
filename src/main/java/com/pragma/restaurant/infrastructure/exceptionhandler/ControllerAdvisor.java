package com.pragma.restaurant.infrastructure.exceptionhandler;

import com.pragma.restaurant.domain.exception.DomainException;
import com.pragma.restaurant.domain.exception.NotOwnerException;
import com.pragma.restaurant.domain.exception.NotRestaurantOwnerException;
import com.pragma.restaurant.domain.exception.UserNotFoundException;
import com.pragma.restaurant.infrastructure.exception.AlreadyClientOrderActiveException;
import com.pragma.restaurant.infrastructure.exception.CategoryNotFoundException;
import com.pragma.restaurant.infrastructure.exception.DishNoFoundException;
import com.pragma.restaurant.infrastructure.exception.NoDataFoundException;
import com.pragma.restaurant.infrastructure.exception.NotRestaurantEmployeeException;
import com.pragma.restaurant.infrastructure.exception.OrderAlreadyAssignedException;
import com.pragma.restaurant.infrastructure.exception.OrderNotActiveException;
import com.pragma.restaurant.infrastructure.exception.OrderNotFoundException;
import com.pragma.restaurant.infrastructure.exception.RestaurantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotOwnerException.class)
    public ResponseEntity<Map<String, String>> handleNotOwnerException(
             NotOwnerException notOwnerException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NOT_OWNER.getMessage()));
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, String>> handleDomainException(
            DomainException domainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, domainException.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(
            CategoryNotFoundException categoryNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotFoundException(
            RestaurantNotFoundException restaurantNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.RESTAURANT_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(DishNoFoundException.class)
    public ResponseEntity<Map<String, String>> handleDishNoFoundException(
            DishNoFoundException dishNoFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.DISH_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(NotRestaurantOwnerException.class)
    public ResponseEntity<Map<String, String>> handleNotRestaurantOwnerException(
            NotRestaurantOwnerException notOwnerException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NOT_RESTAURANT_OWNER.getMessage()));
    }

    @ExceptionHandler(AlreadyClientOrderActiveException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyClientOrderActiveException(
            AlreadyClientOrderActiveException alreadyClientOrderActiveException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ALREADY_CLIENT_ORDER_ACTIVE.getMessage()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOrderNotFoundException(
            OrderNotFoundException orderNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ORDER_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(NotRestaurantEmployeeException.class)
    public ResponseEntity<Map<String, String>> handleNotRestaurantEmployeeException(
            NotRestaurantEmployeeException notRestaurantEmployeeException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NOT_RESTAURANT_EMPLOYEE.getMessage()));
    }

    @ExceptionHandler(OrderAlreadyAssignedException.class)
    public ResponseEntity<Map<String, String>> handleOrderAlreadyAssignedException(
            OrderAlreadyAssignedException handOrderAlreadyAssignedException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ORDER_ALREADY_ASSIGNED.getMessage()));
    }

    @ExceptionHandler(OrderNotActiveException.class)
    public ResponseEntity<Map<String, String>> handleOrderNotActiveException(
            OrderNotActiveException orderNotActiveException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ORDER_NOT_ACTIVE.getMessage()));
    }
}