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

/**
 * @author Alexander Kaleganov
 * @since 13/06/2019
 *
 */
public class Dispatch {

    private final Map<String, Function<Item, Optional>> dispatch = new HashMap<String, Function<Item, Optional>>();
    private final static Dispatch INSTANCE = new Dispatch().init();
    private static final Logger LOGGER = Logger.getLogger(Dispatch.class);
    private Store<Item> store = DbStore.getDbStore();
    public static Dispatch getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial handlers.
     *
     * @return current object.
     */
    public Dispatch init() {
        this.dispatch.put("findById", (item) ->
                Optional.of(this.store.findById(item)));
        this.dispatch.put("list", (item) ->
                Optional.of(this.store.findAll()));
        return this;
    }

    /**
     * новый диспатчер, возвращает объект в зависимости от стринг значения либо лист
     * либо объект по id можно увеличить функционал за счёт метода init()
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