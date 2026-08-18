package ru.yandex.practicum;

public class WordleStartupException extends RuntimeException {
    public WordleStartupException(String message) {
        super(message);
    }

    public WordleStartupException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
