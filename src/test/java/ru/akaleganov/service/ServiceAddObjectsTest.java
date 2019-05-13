package ru.akaleganov.service;

import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;
import ru.akaleganov.modelsxml.Announcement;
import ru.akaleganov.modelsxml.Car;

import java.io.IOException;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;

public class ServiceAddObjectsTest {
    private void testfank(Consumer<Session> test) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        test.accept(session);
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    @Test
    public void testAddac() throws IOException {
        String jsonCar = "{\"marka\":{\"id\":\"1\"}, \"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
        Car car = new ServiceAddObjects().addCar(jsonCar);
        System.out.println(car);
        String annuoncment = "{\"name\":\"продам машину\", \"description\":\"описание\", \"author\":{\"id\":\"1\"}, \"car\":" + jsonCar + "}";
        Announcement ann = new ServiceAddObjects().addAnnouncement(annuoncment);
        ann.getCar().setAnnouncement(ann);
        System.out.println(ann);
        testfank(se -> {
            se.save(ann);
            Announcement deleteA = se.get(Announcement.class, ann.getId());
            se.delete(deleteA);
        });
    }

    @Test
    public void testFormat() {
        String url = "db/Avito-Shema.png";
        String expected = "";
        for (String temp : url.split("\\.")) {
            expected = temp;
        }
        assertThat(expected, Is.is("png"));
    }
}