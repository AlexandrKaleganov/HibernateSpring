package ru.akaleganov.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.*;

public class CarTest {
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
    public void testCar() {
        String jsonCar = "{\"id\":\"0\"}";
    }
}