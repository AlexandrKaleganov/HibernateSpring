package ru.akaleganov.db;

import java.util.List;

/**
 * @author Alexander Kaleganov
 * @since 13/06/2019
 *
 * @param <E> объект с которым работает бд
 */
public interface Store<E> {
    /**
     * нахожждение лота по id
     * @param item объект который необходимо получить из бд
     * @return Получение назад обработанного объекта
     */
    E findById(E item);

    /**
     *
     * @return получение списка объектов из бд
     */
    List<E> findAll();
}
