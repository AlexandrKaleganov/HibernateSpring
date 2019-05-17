package ru.akaleganov.dbmanag;

import java.util.List;

/**
 * @author Alexander Kaleganov
 * @since 17.05.2019
 * @param <E>
 */
public interface Store<E> {
    E add(E e);

    E delete(E e);

    E edit(E e);

    List<E> findAll();

    E findByID(E e);

    E findByName(E e);
}
