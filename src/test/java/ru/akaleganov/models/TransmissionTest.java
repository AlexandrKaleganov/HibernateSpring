package ru.akaleganov.models;

import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class TransmissionTest {
    private void testfank(Consumer<Session> test) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        test.accept(session);
        session.close();
        factory.close();

    }

    @Test
    public void testTrs() {
        this.testfank(session -> {
        ArrayList<String> expexted = new ArrayList<>(Arrays.asList("АВТОМАТ", "МЕХАНИЧ", "ВАРИАТОР", "РОБОТ"));
        ArrayList<Transmission> actual = (ArrayList<Transmission>) session.createQuery("from Transmission").list();
            for (int i = 0; i < expexted.size(); i++) {
                assertThat(actual.get(i).getName(), Is.is(expexted.get(i)));
            }
        });
    }

}