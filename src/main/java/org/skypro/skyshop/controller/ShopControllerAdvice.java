package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.exception.ShopException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// Аннотация, данный класс будет использоваться как глобальный обработчик исключений
// для всех контроллеров приложения.
@ControllerAdvice
public class ShopControllerAdvice {

    // Эта аннотация указывает, что все исключения этого класса будут
    // обработаны вручную, в частности, этим методом
    @ExceptionHandler(NoSuchProductException.class)
    ResponseEntity<ShopException> noSuchProduct(NoSuchProductException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ShopException(e.getMessage()));

    }
}
