package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.exception.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// Аннотация, данный класс будет использоваться как глобальный обработчик исключений
// для всех контроллеров приложения.
@ControllerAdvice
public class ShopControllerAdvice {

    // Аннотация, указывающая метод, который будет вызываться
    // при возникновении определенного типа исключения
    @ExceptionHandler(NoSuchProductException.class)
    ResponseEntity<ShopError> noSuchProduct(NoSuchProductException e) {
        return new ResponseEntity<>(
                new ShopError("NOT_FOUND", e.getMessage()), // Тело ответа
                HttpStatus.NOT_FOUND);                            // HTTP статус (NOT_FOUND)
    }
}
