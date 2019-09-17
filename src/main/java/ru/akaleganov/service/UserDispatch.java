package ru.akaleganov.service;

import org.apache.log4j.Logger;
import ru.akaleganov.dbmanag.*;
import ru.akaleganov.modelsannot.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Класс UserDispatch
 *
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 * <p>
 * UserDispatch мой  универсальный диспатч
 * буду передовать объекты Пользователей  а возвращать в
 * зависимости от вызванного метода
 */
public class UserDispatch {

    private static final Logger LOGGER = Logger.getLogger(UserDispatch.class);

    private final Map<String, Function<Users, Optional>> dispatch = new HashMap<>();
    private final static UserDispatch INSTANCE = new UserDispatch().init();

    public static UserDispatch getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial hashmap functional.
     */
    public UserDispatch init() {
        //упавление пользователями
        this.dispatch.put("findByLoginPass", (ticket) ->
                Optional.of(UsersDb.getInstance().findByLoginPass(ticket)));
        this.dispatch.put("getListUser", (ticket) ->
                Optional.of(UsersDb.getInstance().findAll()));
        this.dispatch.put("findByLogin", (ticket) ->
                Optional.of(UsersDb.getInstance().findByLogin(ticket)));
        this.dispatch.put("findByIdUser", (ticket) ->
                Optional.of(UsersDb.getInstance().findByID(ticket)));
        this.dispatch.put("deleteUser", (ticket) ->
                Optional.of(UsersDb.getInstance().delete(ticket)));
        this.dispatch.put("addOrUpdate", (ticket) ->
                Optional.of(UsersDb.getInstance().edit(ticket)));
        this.dispatch.put("addUser", (ticket) ->
                Optional.of(UsersDb.getInstance().add(ticket)));
        return this;
    }

    /**
     * @param key    параметр указывает на ключ из хеш мапы, в заваисмости от ключа выбирается реализация функционального метода
     * @param ticket в оптионал передаётся передаётася обект модели {@link (Model, Announcement, Car, Marka, Model, Photo, Roles, Transmission, Users)}
     * @param <E>    параметр который вернётся, возможно это будет Lист объектов из базы, либо один объект
     */
    public <E> E access(String key, Users ticket) {
        //noinspection unchecked,OptionalGetWithoutIsPresent
        LOGGER.info("key = " +  key);
        return (E) this.dispatch.get(key).apply(ticket).get();
    }
}