package ru.akaleganov.modelsxml;

import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.akaleganov.service.ServiceAddObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnnouncementTest {
    private void testfank(Consumer<Session> test) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        test.accept(session);
        session.getTransaction().rollback();
        session.close();
        factory.close();
    }

    @Test
    public void addAndDelete() throws IOException {
        String jsonCar = "{\"marka\":{\"id\":\"1\"}, \"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
        ArrayList<String> urlList = new ArrayList<>(Arrays.asList("db/Avito-Shema.png"));
        String jsonann = "{\"name\":\"продам машину\", \"description\":\"описание\", \"author\":{\"id\":\"1\"}}";
        //в классе ServiceAddObjects все объекты объеденятся и запакуются в один готовый объект для добавления в базу
        Announcement ann = new ServiceAddObjects().addAll(jsonann, jsonCar, urlList);
        testfank(se -> {
            se.save(ann);
            se.createQuery("delete Photo where car_id = :id").setParameter("id", ann.getCar().getId()).executeUpdate();
            se.createQuery("delete Car where announcement_id =  :id").setParameter("id", ann.getId()).executeUpdate();
            se.createQuery("delete Announcement where id = :id").setParameter("id", ann.getId()).executeUpdate();
        });
    }
}