package ru.akaleganov.err;

import java.io.IOException;

/**
 * Класс для исключения в случае если действие указанно не верно,
 */
public class OperationError extends IOException {
    public OperationError() {
        super("действие не распознано повторите попытку");
    }
}
