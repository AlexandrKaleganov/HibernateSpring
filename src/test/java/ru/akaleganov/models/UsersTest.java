package ru.akaleganov.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.*;

public class UsersTest {
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
    public void testUser() {
        this.testfank(session -> {
            Users root = session.get(Users.class, 1);
            assertThat(root.getLogin(), is("root"));
        });
    }
    @Test
    public void testUserAdd() {
        this.testfank(session -> {

            Users users = session.get(Users.class, 2);
            System.out.println(users);
            session.delete(users);
        });
    }
}