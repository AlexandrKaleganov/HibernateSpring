package ru.akaleganov.service;

import ru.akaleganov.models.Item;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * класс будет принимать параметры и возвращать объект item, с заполнением недостающих полей
 */
public class ServiceItem {
    /**
     * получим готовую мапу итемов
     * @param list
     * @return
     */
    public HashMap<Item, Integer> addItem(ArrayList<Item> list) {
        HashMap<Item, Integer> mamrsl = new HashMap<>();
        list.forEach(e->mamrsl.put(e, 0));
        return mamrsl;
    }

}
