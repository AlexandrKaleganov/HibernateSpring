package ru.akaleganov.service;

import ru.akaleganov.models.Item;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * класс будет принимать параметры и возвращать объект item, с заполнением недостающих полей
 */
public class ServiceItem {


    public Item addItem(String desc, String done) {
        Item item = new Item();
        if (done.contains("false")) {
            item.setDone(false);
        } else {
            item.setDone(true);
        }
        item.setDescr(desc);
        item.setCreate(Timestamp.valueOf(LocalDateTime.now()));
        return item;
    }

}
