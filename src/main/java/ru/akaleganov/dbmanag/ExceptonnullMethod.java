package ru.akaleganov.dbmanag;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
public class ExceptonnullMethod extends Exception {
    ExceptonnullMethod() {
        super("Метод не реализован");
    }
}
