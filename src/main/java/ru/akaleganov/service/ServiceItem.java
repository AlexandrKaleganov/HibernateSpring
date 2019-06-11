package ru.akaleganov.service;

import org.apache.log4j.Logger;
import ru.akaleganov.db.DbStore;
import ru.akaleganov.db.Store;
import ru.akaleganov.models.Item;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * класс будет принимать параметры и возвращать объект item, с заполнением недостающих полей
 */
public class ServiceItem {
    /**
     * ServiceItem.
     */

    private final Map<String, BiFunction<Item, HashMap<Item, Integer>, HashMap<Item, Integer>>> servis  =
            new HashMap<String, BiFunction<Item, HashMap<Item, Integer>, HashMap<Item, Integer>>>();
    private final static ServiceItem INSTANCE = new ServiceItem().init();
    private static final Logger LOGGER = Logger.getLogger(ServiceItem.class);

    public static ServiceItem getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial handlers.
     *
     * @return current object.
     */
    public ServiceItem init() {
        this.servis.put("add", (item, map) ->
               this.addtoMap(item, map));
        this.servis.put("delone", (item, map) ->
                 this.deleteOneItem(item, map));
        this.servis.put("deleteitemforkey", (item, map) ->
                this.deleteItem(item, map));
        this.servis.put("clear", (item, map) ->
                this.clearBacket(item, map));
        this.servis.put("findallmap", (item, map) ->
                this.findallmap(item, map));
        return this;
    }

    /**
     * новый диспатчер, возвращает указанный параметр
     *
     * @param key
     * @param item
     * @return
     */
    public HashMap<Item, Integer> access(String key, Item item, HashMap<Item, Integer> map) {
        return this.servis.get(key).apply(item, map);
    }

    /**
     * добавление объекта в мапу
     * @param item
     * @param map
     * @return
     */
    public HashMap<Item, Integer> addtoMap(Item item, HashMap<Item, Integer> map) {
        if (map.containsKey(item)) {
            map.put(item, map.get(item) + 1);
        } else {
            map.put(item, 1);
        }
        return map;
    }
    /**
     * удаление одного объекта из мапы
     * @param item
     * @param map
     * @return
     */
    public HashMap<Item, Integer> deleteOneItem(Item item, HashMap<Item, Integer> map) {
        if (map.containsKey(item)) {
            map.put(item, map.get(item) - 1);
        }
        if (map.get(item) == 0) {
            map.remove(item);
        }
        return map;
    }

    /**
     * удаление всех объектов из мапы по ключу
     * @param item
     * @param map
     * @return
     */
    public HashMap<Item, Integer> deleteItem(Item item, HashMap<Item, Integer> map) {
        if (map.containsKey(item)) {
            map.remove(item);
        }
        return map;
    }
    /**
     * удаление всех объектов из мапы
     * @param item
     * @param map
     * @return
     */
    public HashMap<Item, Integer> clearBacket(Item item, HashMap<Item, Integer> map) {
        map = new HashMap<>();
        return map;
    }

    private  HashMap<Item, Integer> findallmap(Item item, HashMap<Item, Integer> map) {
        return map;
    }
}
