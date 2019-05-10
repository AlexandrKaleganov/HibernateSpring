package ru.akaleganov.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.io.IOException;
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
    public void testCar() throws IOException {
        String jsonCar = "{\"marka\":{\"id\":\"1\"}, \"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
        Car car = new ObjectMapper().readValue(jsonCar, Car.class);
        System.out.println(car);
        testfank(session -> {
            session.save(car);
        });
    }
}