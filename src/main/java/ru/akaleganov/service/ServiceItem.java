package ru.akaleganov.service;

import ru.akaleganov.models.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Класс для работы с хешмапой
 * @author Alexander Kaleganov
 * @since 13/06/2019
 * <br/>
 * <b>содержит поля:<b/>
 * @see ServiceItem#INSTANCE
 * @see ServiceItem#servis
 *
 */
public class ServiceItem {

    /**
     * хешмапа ключ стринг (команды которые вытянут необходимую функцию) и содержит функциональный интерфейс,
     * который принимает хешкарту корзины HashMap<Item, Integer>   - Item  указывает на позицию в корзине,
     * Integer указывает на количество данного лота в корзине, и {@link Item} в зависимости от того какая команда прилетела
     * выполняются необходимые действия с хешкартой
     * add - добавить объект в корзину
     * delOne минусует  от Integer  по ключу Item  одно значение
     * deleteItemForKey удаляет из корзины позицию полностью
     * clear  - очищает корзину(хешмапу)
     *  findAllMap возвращает текущую хемапу
     */
    private final Map<String, BiConsumer<Item, HashMap<Item, Integer>>> servis  =
            new HashMap<>();
    /**
     * синглтон текущего класса
     */
    private final static ServiceItem INSTANCE = new ServiceItem().init();

    /**
     *
     * @return {@link ServiceItem} возвращает объект текущего класса
     */
    public static ServiceItem getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial handlers.
     *
     * @return current object.
     */
    private ServiceItem init() {
        this.servis.put("add", this::addToMap);
        this.servis.put("delOne", this::deleteOneItem);
        this.servis.put("deleteItemForKey", this::deleteItem);
        this.servis.put("clear", (item, map) -> clearBasket(map));
        this.servis.put("findAllMap", (item, map) -> findAllMap());
        return this;
    }

    /**
     * возвращает указанный параметр
     *
     * @param key ключ указывает на действие которое необходимо произвести с HeshMap<Item, Integer>
     * @param item {@link Item}  который возможно будет добавлен или удалён из корзины
     */
    public void access(String key, Item item, HashMap<Item, Integer> map) {
        this.servis.get(key).accept(item, map);
    }

    /**
     * добавление объекта в мапу
     * @param item {@link Item} объект который будет добавлен в корзину
     * @param map  HashMap<Item, Integer> корзина, в которую будет добавлен Item
     */
    private void addToMap(Item item, HashMap<Item, Integer> map) {
        if (map.containsKey(item)) {
            map.put(item, map.get(item) + 1);
        } else {
            map.put(item, 1);
        }
    }
    /**
     * удаление одного объекта из мапы
     * @param item {@link Item} объект который будет удалён из корзины
     * @param map  HashMap<Item, Integer> корзина, из которой будет удалён Item
     */
    private void deleteOneItem(Item item, HashMap<Item, Integer> map) {
        if (map.containsKey(item)) {
            map.put(item, map.get(item) - 1);
        }
        if (map.get(item) == 0) {
            map.remove(item);
        }
    }

    /**
     * удаление всех объектов из мапы по ключу
     * @param item {@link Item} находит объект Item по ключу в хеш мапе и удаляет его
     * @param map  HashMap<Item, Integer> корзина, из которой будет удалён Item
     */
    private void deleteItem(Item item, HashMap<Item, Integer> map) {
        map.remove(item);
    }
    /**
     * удаление всех объектов из мапы
     * @param map ХешМапа получаемая из сессии, которая хранит в себе добавленные в корзину {@link Item}
     */
    private void clearBasket(HashMap<Item, Integer> map) {
        map.clear();
    }

    /**
     * над корзиной не будет произведено никаких действий
     */
    private void findAllMap() {

    }
}
