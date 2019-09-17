package ru.akaleganov.service;

import ru.akaleganov.dbmanag.*;
import ru.akaleganov.modelsannot.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Класс автомобиль, создаётся уникальный объект для каждого объявления
 *
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 * <p>
 * Dispatch мой  универсальный диспатч
 * буду передовать объекты Моделей  а возвращать в
 * зависимости от вызванного метода
 */
public class AnnouncementDispatch {


    private final Map<String, Function<Mod, Optional>> dispatch = new HashMap<>();
    private final static AnnouncementDispatch INSTANCE = new AnnouncementDispatch().init();

    public static AnnouncementDispatch getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial hashmap functional.
     */
    public AnnouncementDispatch init() {
        //управление объявлениями
        this.dispatch.put("addAn", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().edit((Announcement) ticket)));
        this.dispatch.put("findAllAn", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().findAll()));
        this.dispatch.put("findByIdAn", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().findByID((Announcement) ticket)));
        this.dispatch.put("updateAn", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().edit((Announcement) ticket)));
        this.dispatch.put("deleteAn", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().delete((Announcement) ticket)));
         //фильтры
        this.dispatch.put("toShowForTheLastDay", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().toShowForTheLastDay()));
        this.dispatch.put("toShowWithAPhoto", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().toShowWithAPhoto()));
        this.dispatch.put("toShowACertainBrand", (ticket) ->
                Optional.of(AnnouncementDb.getInstance().toShowACertainBrand((Marka) ticket)));

        //управление картинками
        this.dispatch.put("findByIdPhoto", (ticket) ->
                Optional.of(PhotoDb.getInstance().findByID((Photo) ticket)));
        this.dispatch.put("deletePhotoById", (ticket) ->
                Optional.of(PhotoDb.getInstance().delete((Photo) ticket)));
        //управление марками
        this.dispatch.put("findAllMarka", (ticket) ->
                Optional.of(MarkaDb.getInstance().findAll()));
        this.dispatch.put("findByIdMarka", (ticket) ->
                Optional.of(MarkaDb.getInstance().findByID((Marka) ticket)));
        //управление моделями
        this.dispatch.put("findByMarkaIdModel", (ticket) ->
                Optional.of(ModelDb.getInstance().findByMarkaid((Marka) ticket)));
        //управление трансмиссией
        this.dispatch.put("findAllTr", (ticket) ->
                Optional.of(TransmissionDb.getInstance().findAll()));
        return this;
    }

    /**
     * @param key    параметр указывает на ключ из хеш мапы, в заваисмости от ключа выбирается реализация функционального метода
     * @param ticket в оптионал передаётся передаётася обект модели {@link (Model, Announcement, Car, Marka, Model, Photo, Transmission)}
     * @param <E>    параметр который вернётся, возможно это будет Lист объектов из базы, либо один объект
     */
    public <E> E access(String key, Mod ticket) {
        //noinspection unchecked,OptionalGetWithoutIsPresent
        return (E) this.dispatch.get(key).apply(ticket).get();
    }
}