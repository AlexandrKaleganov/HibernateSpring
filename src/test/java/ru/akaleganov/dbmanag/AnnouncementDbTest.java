package ru.akaleganov.dbmanag;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.akaleganov.modelsannot.Announcement;
import ru.akaleganov.service.ServiceAddObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class AnnouncementDbTest {
    private String jsonCar = "{\"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
    private ArrayList<String> urlList = new ArrayList<>(Arrays.asList("db/Avito-Shema.png"));
    private String jsonann = "{\"name\":\"продам машину\", \"author\":{\"id\":\"1\"}}";
    //в классе ServiceAddObjects все объекты объеденятся и запакуются в один готовый объект для добавления в базу
    private Announcement ann = new ServiceAddObjects().addAll(jsonann, jsonCar, urlList);

    private void testAll(BiConsumer<AnnouncementDb, Announcement> fank) {

        Announcement announcement = AnnouncementDb.getInstance().add(new ServiceAddObjects().addAll(jsonann, jsonCar, urlList));
        try {
            fank.accept(AnnouncementDb.getInstance(), announcement);
        } finally {
            new AnnouncementDb().delete(announcement);
        }
    }

    @Test
    public void add() {
        testAll((db, ann) -> {
            assertTrue(ann.getId() > 0);
        });
    }

    @Test
    public void delete() {
        testAll((db, ann) -> {
            db.delete(ann);
            assertTrue(db.findByID(ann) == null);
        });
    }

    @Test
    public void edit() {
        testAll((db, ann) -> {
            ann.getCar().setDescription("изменено описание");
            ann.setName("изменено");
            db.edit(ann);
            Announcement expected = db.findByID(ann);
            assertTrue(expected.getName().contains("изменено"));
            assertTrue(expected.getCar().getDescription().contains("изменено описание"));
        });
    }

    @Test
    public void findAll() {
        testAll((db, ann) -> {
            assertTrue(db.findAll().size() > 0);
        });
    }

    @Test
    public void findByID() {
        testAll((db, ann) -> {
            Announcement expected = db.findByID(ann);
            assertTrue(expected.getName().contains("продам машину"));
        });
    }

    @Test
    public void findByName() {
        testAll((db, ann) -> {
            assertTrue(db.findByName(ann).get(0).getName().contains("продам машину"));
        });
    }

    @Test
    public void findByLoginPass() {
        testAll((db, ann) -> {
        });
    }
}