package ru.akaleganov.service;

import ru.akaleganov.dbmanag.UsersDb;
import ru.akaleganov.modelsannot.Announcement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import org.apache.log4j.Logger;
import ru.akaleganov.modelsannot.Users;

/**
 * Dispatch мой любимый универсальный диспатч
 * буду передовать объект Announcement  а возвращать в
 * зависимости от вызванного метода
 */
public class Dispatch {


    private final Map<String, Function<Optional, Optional>> dispatch = new HashMap<String, Function<Optional, Optional>>();

    private final static Dispatch INSTANCE = new Dispatch().init();
    private static final Logger LOGGER = Logger.getLogger(Dispatch.class);

    public static Dispatch getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial handlers.
     *
     * @return current object.
     */
    public Dispatch init() {
        this.dispatch.put("findByLoginPass", (ticket) ->
                Optional.of(UsersDb.getInstance().findByLoginPass((Users) ticket.get())));
//        this.dispatch.put("getListHall", (ticket) ->
//                Optional.of(valid.getListCell()));
//        this.dispatch.put("isChecked", (ticket) ->
//                Optional.of(valid.isCheckedCell(ticket.getCell())));
//        this.dispatch.put("clear", (ticket) -> {
//            this.valid.deleteAll();
//            return Optional.of(true);
//        });
        return this;
    }

    /**
     * параметр указывает на то , какой тип данных мы получи
     *
     * @param key
     * @param ticket
     * @param <E>
     * @return
     * @throws Exception
     */
    public <E> E access(String key, Optional ticket) {
        Optional<E> rsl = Optional.empty();
        rsl = this.dispatch.get(key).apply(ticket);
        return rsl.get();
    }
}