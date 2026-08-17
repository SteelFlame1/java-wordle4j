package ru.yandex.practicum;

public class UnknownWordException extends RuntimeException {
    public UnknownWordException(String message) {
        super(message);
    }
}
