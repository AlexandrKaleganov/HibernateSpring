package ru.akaleganov.modelsannot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class RuchkiTest {
    @Test
    public void testPinal() throws IOException {
        String json = "{\"name\":\"pinal\", \"ruchkis\":[{\"name\":\"ruchka1\"}, {\"name\":\"ruchka2\"}]}";
        Pinal pinal = new ObjectMapper().readValue(json, Pinal.class);
        System.out.println(pinal);
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        Session session = factory.openSession();
//        session.save(pinal);
    }

}