package ru.akaleganov.dbmanag;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runners.model.Annotatable;
import ru.akaleganov.modelsannot.Announcement;
import ru.akaleganov.modelsannot.Car;
import ru.akaleganov.service.ServiceAddObjects;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class CarDbTest {
    private String jsonAnn = "{\"name\":\"продам машину\", \"author\":{\"id\":\"1\"}}";
    private String jsonCar = "{\"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
    private Announcement ann = new ServiceAddObjects().addAll(jsonAnn, jsonCar, new ArrayList<>());

    private void testF(Consumer<Session> sessionConsumer) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        sessionConsumer.accept(session);
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    @Test
    public void edit() {
        testF(session -> session.save(ann));
        ann.getCar().setDescription("описание объявления");
        Car expected = CarDb.getInstance().edit(ann.getCar());
        assertTrue(expected.getDescription().contains("описание объявления"));
        testF(session -> {
            Announcement expected2 = session.load(Announcement.class, ann.getId());
            assertTrue(expected2.getCar().getDescription().contains("описание объявления"));
            session.delete(expected2);
        });
    }
}