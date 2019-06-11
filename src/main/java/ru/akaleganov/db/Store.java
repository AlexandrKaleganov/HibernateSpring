package ru.akaleganov.db;

import java.util.List;

/**
 * интерфейс, описывающий реализацию управление БД
 * @param <E>
 */
public interface Store<E> {
    /* методы который не понадобились
//    E add(E item);

//    E delete(E item);

//    E update(E item);
*/

    /**
     * нахожждение лота по id
     * @param item
     * @return
     */
    E findbyID(E item);

    /**
     * получение списка лотов
     * @return
     */
    List<E> findall();
}
