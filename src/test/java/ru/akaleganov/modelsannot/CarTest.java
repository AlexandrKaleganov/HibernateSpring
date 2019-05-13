package ru.akaleganov.modelsannot;

import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.akaleganov.service.ServiceAddObjects;
import ru.akaleganov.service.ServiceAddObjectsTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class CarTest {
    private void fank(Consumer<Session> f) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            f.accept(session);
            session.getTransaction().rollback();
        }
    }

    @Test
    public void testTransmission() {
        String jsonCar = "{\"marka\":{\"id\":\"1\"}, \"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
        Car car = new ServiceAddObjects().addCar(jsonCar);
        this.fank(session -> {
            session.save(car);
            assertThat(car.getId() > 0, Is.is(true));
            assertThat(car.getMarka().getId(), Is.is(1));
            assertThat(car.getModel().getId(), Is.is(1));

        });
    }
}