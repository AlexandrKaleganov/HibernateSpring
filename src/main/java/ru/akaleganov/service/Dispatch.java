package ru.akaleganov.service;

import ru.akaleganov.dbmanag.AnnouncementDb;
import ru.akaleganov.dbmanag.PhotoDb;
import ru.akaleganov.dbmanag.RolesDb;
import ru.akaleganov.dbmanag.UsersDb;
import ru.akaleganov.modelsannot.Announcement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.apache.log4j.Logger;
import ru.akaleganov.modelsannot.Photo;
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
        //упавление пользователями
        this.dispatch.put("findByLoginPass", (ticket) ->
                Optional.of(UsersDb.getInstance().findByLoginPass((Users) ticket.get())));
        this.dispatch.put("getListUser", (ticket) ->
                Optional.of(UsersDb.getInstance().findAll()));
        this.dispatch.put("findByLogin", (ticket) ->
                Optional.of(UsersDb.getInstance().findByLogin((Users) ticket.get())));
        this.dispatch.put("findbyiduser", (ticket) ->
                Optional.of(UsersDb.getInstance().findByID((Users) ticket.get())));
        this.dispatch.put("deleteuser", (ticket) ->
                Optional.of(UsersDb.getInstance().delete((Users) ticket.get())));
        this.dispatch.put("addOrupdate", (ticket) ->
                Optional.of(UsersDb.getInstance().edit((Users) ticket.get())));
        //управление ролями
        this.dispatch.put("findallroles", (ticket) ->
                Optional.of(RolesDb.getInstance().findAll()));
        //управление объявлениями
        this.dispatch.put("findallan", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().findAll()));
        this.dispatch.put("findbyidan", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().findByID((Announcement) ticket.get())));
        this.dispatch.put("updatean", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().edit((Announcement) ticket.get())));
        //управление картинками
        this.dispatch.put("findbyidphoto", (ticket) ->
                Optional.of(PhotoDb.getInstance().findByID((Photo) ticket.get())));
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