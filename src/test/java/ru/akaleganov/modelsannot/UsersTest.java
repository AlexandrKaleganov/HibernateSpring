package ru.akaleganov.modelsannot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.akaleganov.service.ServiceAddObjects;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;


public class UsersTest {
    private void fank(Consumer<Session> function) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            function.accept(session);
            session.getTransaction().commit();
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

    @Test
    public void addUser() {
        String jsonUser = "{\"name\":\"name\", "
                + "\"login\":\"login\", "
                + "\"roles\": {\"id\": \"2\""
                + ", \"role\": \"USER\""
                + "}," + " \"password\":\"pass\"}";
        Users users = new ServiceAddObjects().addUser(jsonUser);
        this.fank(s -> {
            s.save(users);
            Users rsl = s.load(Users.class, users.getId());
            System.out.println("LOAD 1  " + rsl);
        });
        this.fank(s -> {
            Users rsl = s.load(Users.class, users.getId());
            System.out.println("LOAD 2  " + rsl);
            s.delete(rsl);
        });
    }
}