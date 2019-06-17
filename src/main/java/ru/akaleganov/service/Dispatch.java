package ru.akaleganov.service;

import ru.akaleganov.db.DbStore;
import ru.akaleganov.db.Store;
import ru.akaleganov.models.Item;

import java.util.*;
import java.util.function.Function;

/**
 * Класс диспетчер комманд
 * в хешмапе содержаться 2 команды
 * findById  и  list реализованы
 * с помощью функционального интерфейса
 * @author Alexander Kaleganov
 * @since 13/06/2019
 * <br/>
 * <b>содержит поля:<b/>
 * @see Dispatch#dispatch
 * @see Dispatch#store
 * @see Dispatch#INSTANCE
 *
 */
public class Dispatch {
    /**
     * мапа с функциональным интерфейсом, будет принимать {@link Item} и возвращает оптионал в котором содержится {@link Item} или List<Item>
     */
    private final Map<String, Function<Item, Optional>> dispatch = new HashMap<>();
    /**
     * синглтон {@link Dispatch}
     */
    private final static Dispatch INSTANCE = new Dispatch().init();
    /**
     * синглтон экземпляр класса {@link DbStore} для управления базой данных
     */
    private final Store<Item> store = DbStore.getDbStore();

    public static Dispatch getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial handlers.
     *
     * @return current object.
     */
    private Dispatch init() {
        this.dispatch.put("findById", (item) ->
                Optional.of(this.store.findById(item)));
        this.dispatch.put("list", (item) ->
                Optional.of(this.store.findAll()));
        return this;
    }

    /**
     * новый диспатчер, возвращает объект в зависимости от стринг значения либо лист
     * либо объект по id можно увеличить функционал за счёт метода init()
     * @param key ключ хеш мапы по которому вызовится необходимый метод
     * @param item {@link Item} входящий параметр
     * @return вернёт {@link Item} по id  или List<Item>
     */
    public <E> E access(String key, Item item) {
        //noinspection unchecked
        Optional<E> rsl = this.dispatch.get(key).apply(item);
        //noinspection OptionalGetWithoutIsPresent
        return rsl.get();
    }

}