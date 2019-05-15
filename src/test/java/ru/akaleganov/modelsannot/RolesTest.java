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


public class RolesTest {

    private void fank(Consumer<Session> function) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            function.accept(session);
            session.getTransaction().rollback();
        }
    }

    @Test
    public void testRoles() {
        this.fank(s -> {
            ArrayList<Roles> roles = new ArrayList<Roles>();
            roles.addAll(s.createQuery("from Roles").list());
            ArrayList<String> expected = new ArrayList<>(Arrays.asList("ADMIN", "USER"));
            for (int i = 0; i < roles.size(); i++) {
                assertThat(roles.get(i).getRole(), Is.is(expected.get(i)));
            }
        });
    }
}