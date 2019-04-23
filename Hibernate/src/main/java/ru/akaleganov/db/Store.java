package ru.akaleganov.db;

import java.util.List;

public interface Store<E> {
    E add(E item);

    E delete(E item);

    E update(E item);

    E findbyID(E item);

    List<E> findall();
    List<E> findallnotDone();
}
