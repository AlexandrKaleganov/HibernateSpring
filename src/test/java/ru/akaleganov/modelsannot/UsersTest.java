package ru.akaleganov.modelsannot;

import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;


public class UsersTest {
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
            ArrayList<Users> users = new ArrayList<>();
            users.addAll(s.createQuery("from Users").list());
            assertThat(users.get(0).getLogin(), Is.is("root"));
            assertThat(users.get(0).getRoles().getRole(), Is.is("ADMIN"));
        });
    }
}