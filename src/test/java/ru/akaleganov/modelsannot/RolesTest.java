package ru.akaleganov.modelsannot;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.junit.Assert.*;

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
        this.fank(s->{
            ArrayList<Roles> roles = (ArrayList<Roles>) s.createQuery("from Roles").list();
            roles.forEach(System.out::println);
        });
    }
}