package ru.akaleganov.service;

import java.io.IOException;

/**
 * функциональный интерфейс для работы с экцепшенами
 * @author Alexander Kaleganov
 * @since 13/06/2019
 * @version 1.0
 *
 * @param <E> входящий параметр
 * @param <R> то что должен вернуть метод
 */
public interface FankEx<E, R> {
    R submit(E e) throws IOException;
}
