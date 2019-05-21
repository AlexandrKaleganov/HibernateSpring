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
    private String jsonCar = "{\"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
    private Car car = new ServiceAddObjects().addCar(jsonCar);

    private void fank(Consumer<Session> f) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            f.accept(session);
            session.getTransaction().commit();
        }
    }

    private void loadData(Consumer<Session> test) {
        try {
            fank(session -> {
                session.save(car);
            });
            fank(session -> test.accept(session));
        } finally {
            fank(session -> {
                Car del = session.get(Car.class, car.getId());
                session.delete(del);
            });
        }
    }

    @Test
    public void testTransmission() {
        this.loadData(session -> {
            Car temp = session.get(Car.class, car.getId());
            System.out.println(temp);
            assertThat(temp.getId() > 0, Is.is(true));
            assertThat(temp.getModel().getMarka().getId(), Is.is(1));
            assertThat(temp.getModel().getId(), Is.is(1));

        });
    }
}