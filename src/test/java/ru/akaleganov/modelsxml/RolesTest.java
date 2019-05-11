package ru.akaleganov.modelsxml;

import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.akaleganov.modelsxml.Roles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;


public class RolesTest {
    private void masterTest(Consumer<Session> fank) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        fank.accept(session);
        session.getTransaction().rollback();
        session.close();
        factory.close();
    }

    @Test
    public void testRoles() {
        this.masterTest((session) -> {
            ArrayList<String> expected = new ArrayList<>(Arrays.asList("ADMIN", "USER"));
            ArrayList<Roles> actual = (ArrayList<Roles>) session.createQuery("from Roles ").list();
            for (int i = 0; i < expected.size(); i++) {
                assertThat(actual.get(i).getRole(), Is.is(expected.get(i)));
            }
        });
    }

}