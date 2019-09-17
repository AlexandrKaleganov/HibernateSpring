package ru.akaleganov.service;

import ru.akaleganov.dbmanag.*;
import ru.akaleganov.modelsannot.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Класс RoleDispatch
 *
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 * <p>
 * RoleDispatch ролей а возвращать в
 * зависимости от вызванного метода
 */
public class RoleDispatch {


    private final Map<String, Function<Mod, Optional>> dispatch = new HashMap<>();
    private final static RoleDispatch INSTANCE = new RoleDispatch().init();

    public static RoleDispatch getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial hashmap functional.
     */
    public RoleDispatch init() {
        //управление ролями
        this.dispatch.put("findAllRoles", (ticket) ->
                Optional.of(RolesDb.getInstance().findAll()));
        return this;
    }

    /**
     * @param key    параметр указывает на ключ из хеш мапы, в заваисмости от ключа выбирается реализация функционального метода
     * @param ticket в оптионал передаётся передаётася обект модели {@link ( Roles)}
     * @param <E>    параметр который вернётся, возможно это будет Lист объектов из базы, либо один объект
     */
    public <E> E access(String key, Roles ticket) {
        //noinspection unchecked,OptionalGetWithoutIsPresent
        return (E) this.dispatch.get(key).apply(ticket).get();
    }
}