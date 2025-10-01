package org.skypro.skyshop.model.exception;

// Создаем Исключение
public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(){
        super("Товар не найден!");
    }
}
