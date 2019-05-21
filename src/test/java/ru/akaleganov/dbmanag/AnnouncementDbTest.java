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
    private String jsonCar = "{\"marka\":{\"id\":\"1\"}, \"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
    private ArrayList<String> urlList = new ArrayList<>(Arrays.asList("db/Avito-Shema.png"));
    private String jsonann = "{\"name\":\"продам машину\", \"author\":{\"id\":\"1\"}}";
    //в классе ServiceAddObjects все объекты объеденятся и запакуются в один готовый объект для добавления в базу
    private Announcement ann = new ServiceAddObjects().addAll(jsonann, jsonCar, urlList);

    private void functiron(Consumer<Session> test) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        test.accept(session);
        session.beginTransaction().commit();
        session.close();
        factory.close();
    }

    @Before
    public void fankreset() {
        this.functiron(session -> session.save(this.ann));
    }

    @After
    public void fanend() {
        this.functiron(session -> {
            Announcement andel = session.get(Announcement.class, ann.getId());
            session.delete(andel);
        });
    }

    @Test
    public void add() {

    }

    @Test
    public void delete() {
    }

    @Test
    public void edit() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByID() {
    }

    @Test
    public void findByName() {
    }

    @Test
    public void findByLogin() {
    }
}