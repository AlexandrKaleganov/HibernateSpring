package ru.akaleganov.service;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.akaleganov.models.Item;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * тестирование классе сервис итем тест
 */
public class ServiceItemTest {
    /**
     * получение Item all map
     */
    @Test
    public void findall() {
        HashMap<Item, Integer> map = new HashMap<>();
        map.put(new Item((long) 1), 1);
        map.put(new Item((long) 2), 1);
        HashMap<Item, Integer> rsl = ServiceItem.getInstance().access("findAllMap", new Item(), map);
        map.forEach((k, v) -> {
            assertThat(v, Is.is(1));
        });
    }

    /**
     * добавление в мапу объекта
     */
    @Test
    public void add() {
        HashMap<Item, Integer> map = new HashMap<>();
        Item item = new Item((long) 1);
        map.put(item, 1);
        map.put(new Item((long) 2), 1);
        HashMap<Item, Integer> rsl = ServiceItem.getInstance().access("add", item, map);
            assertThat(map.get(item), Is.is(2));
    }
}