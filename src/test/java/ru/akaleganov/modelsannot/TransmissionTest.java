package ru.akaleganov.modelsannot;

import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;


public class TransmissionTest {
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
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("АВТОМАТ", "МЕХАНИЧ", "ВАРИАТОР", "РОБОТ"));

        this.fank(session -> {
            ArrayList<Transmission> rt = (ArrayList<Transmission>) session.createQuery("from Transmission").list();
            for (int i = 0; i < rt.size(); i++) {
                assertThat(rt.get(i).getName(), Is.is(expected.get(i)));
            }
        });
    }

}