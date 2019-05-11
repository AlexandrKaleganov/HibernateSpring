package ru.akaleganov.modelsxml;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.akaleganov.modelsxml.Users;

import java.io.IOException;
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

    /**
     * так планирую добавлть пользователей
     */
    @Test
    public void testUserAdd() {
        this.testfank(session -> {
            ObjectMapper mapper = new ObjectMapper();
            String addUsers = "{\"name\":\"vasia\", \"login\":\"user\",\"roles\":{\"id\":\"1\", \"role\":\"ADMIN\"}, \"password\":\"123\"}";
            Users users = null;
            try {
                users = mapper.readValue(addUsers, Users.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            session.save(users);
            assert users != null;
            Users users1 = session.load(Users.class, users.getId());
            assertThat(users1.getLogin(), Is.is("user"));
        });
    }
}