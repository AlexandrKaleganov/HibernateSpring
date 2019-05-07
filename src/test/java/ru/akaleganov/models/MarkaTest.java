package ru.akaleganov.models;

import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;


public class MarkaTest {

    public void testMarkaModel(BiConsumer<HashMap<String, ArrayList<String>>, Session> fank) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        HashMap<String, ArrayList<String>> mapExpected = new HashMap<String, ArrayList<String>>();
        mapExpected.put("Toyota", new ArrayList<>(Arrays.asList("RAV",
                "Corolla",
                "Corolla Runx",
                "Corolla Alex",
                "Filder",
                "Carina")));
        mapExpected.put("Nissan", new ArrayList<>(Arrays.asList("Sunny",
                "Bluebird Sylphy",
                "Qashqai",
                "Skyline")));
        mapExpected.put("Honda", new ArrayList<>(Arrays.asList("Civic",
                "Stream",
                "CR-V")));
        fank.accept(mapExpected, session);
        session.getTransaction().rollback();
    }

    @Test
    public void testModel() {
        this.testMarkaModel((mapExpected, session) -> {

        });
    }

}