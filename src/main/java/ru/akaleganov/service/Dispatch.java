package ru.akaleganov.service;

import ru.akaleganov.dbmanag.*;
import ru.akaleganov.modelsannot.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Dispatch мой  универсальный диспатч
 * буду передовать объекты Моделей  а возвращать в
 * зависимости от вызванного метода
 */
public class Dispatch {


    private final Map<String, Function<Mod, Optional>> dispatch = new HashMap<>();
    private final static Dispatch INSTANCE = new Dispatch().init();

    public static Dispatch getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial handlers.
     */
    public Dispatch init() {
        //упавление пользователями
        this.dispatch.put("findByLoginPass", (ticket) ->
                Optional.of(UsersDb.getInstance().findByLoginPass((Users) ticket)));
        this.dispatch.put("getListUser", (ticket) ->
                Optional.of(UsersDb.getInstance().findAll()));
        this.dispatch.put("findByLogin", (ticket) ->
                Optional.of(UsersDb.getInstance().findByLogin((Users) ticket)));
        this.dispatch.put("findbyiduser", (ticket) ->
                Optional.of(UsersDb.getInstance().findByID((Users) ticket)));
        this.dispatch.put("deleteuser", (ticket) ->
                Optional.of(UsersDb.getInstance().delete((Users) ticket)));
        this.dispatch.put("addOrupdate", (ticket) ->
                Optional.of(UsersDb.getInstance().edit((Users) ticket)));
        this.dispatch.put("addus", (ticket) ->
                Optional.of(UsersDb.getInstance().add((Users) ticket)));
        //управление ролями
        this.dispatch.put("findallroles", (ticket) ->
                Optional.of(RolesDb.getInstance().findAll()));
        //управление объявлениями
        this.dispatch.put("addan", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().edit((Announcement) ticket)));
        this.dispatch.put("findallan", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().findAll()));
        this.dispatch.put("findbyidan", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().findByID((Announcement) ticket)));
        this.dispatch.put("updatean", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().edit((Announcement) ticket)));
        //управление картинками
        this.dispatch.put("findbyidphoto", (ticket) ->
                Optional.of(PhotoDb.getInstance().findByID((Photo) ticket)));
        //управление марками
        this.dispatch.put("findallmarka", (ticket) ->
                Optional.of(MarkaDb.getInstance().findAll()));
        this.dispatch.put("findbyidmarka", (ticket) ->
                Optional.of(MarkaDb.getInstance().findByID((Marka) ticket)));
        //управление моделями
        this.dispatch.put("findByMarkaidModel", (ticket) ->
                Optional.of(ModelDb.getInstance().findByMarkaid((Marka) ticket)));
        //управление трансмиссией
        this.dispatch.put("findalltr", (ticket) ->
                Optional.of(TransmissionDb.getInstance().findAll()));
        return this;
    }

    /**
     * @param key    параметр указывает на ключ из хеш мапы, в заваисмости от ключа выбирается реализация функционального метода
     * @param ticket в оптионал передаётся передаётася обект модели {@link (Model, Announcement, Car, Marka, Model, Photo, Roles, Transmission, Users)}
     * @param <E>    параметр который вернётся, возможно это будет Lист объектов из базы, либо один объект
     */
    @SuppressWarnings("unchecked")
    public <E> E access(String key, Mod ticket) {
        return (E) this.dispatch.get(key).apply(ticket);
    }
}