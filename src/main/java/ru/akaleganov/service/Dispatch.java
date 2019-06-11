package ru.akaleganov.service;

/**
 * класс диспетчер комманд
 * в хешмапе содержаться 2 команды
 *   findbyID  и  list реализованы
 * с помощью функционального интерфейса
 *
 * метод init  инициализирует нашу мапу
 * метод access получает доступ к нужной функции на входи идёт валидатор, ключ и созданный пользователь
 */

import org.apache.log4j.Logger;
import ru.akaleganov.db.DbStore;
import ru.akaleganov.db.Store;
import ru.akaleganov.models.Item;

import java.util.*;
import java.util.function.Function;

public class Dispatch {
    /**
     * Dispatch.
     */

    private final Map<String, Function<Item, Optional>> dispatch = new HashMap<String, Function<Item, Optional>>();
    private final static Dispatch INSTANCE = new Dispatch().init();
    private static final Logger LOGGER = Logger.getLogger(Dispatch.class);
    Store<Item> store = DbStore.getDbstoreINSTANCE();
    public static Dispatch getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial handlers.
     *
     * @return current object.
     */
    public Dispatch init() {
        this.dispatch.put("findbyid", (item) ->
                Optional.of(this.store.findbyID(item)));
        this.dispatch.put("list", (item) ->
                Optional.of(this.store.findall()));
        return this;
    }

    /**
     * новый диспатчер, возвращает указанный параметр
     * @param key
     * @param item
     * @param <E>
     * @return
     */
    public <E> E access(String key, Item item) {
        Optional<E> rsl = Optional.empty();
        rsl = this.dispatch.get(key).apply(item);
        return rsl.get();
    }

}