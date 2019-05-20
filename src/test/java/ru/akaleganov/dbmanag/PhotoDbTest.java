package ru.akaleganov.dbmanag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hamcrest.Factory;
import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.akaleganov.modelsannot.Announcement;
import ru.akaleganov.service.ServiceAddObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiConsumer;

import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * замороченно получилось, бедав том что пришлось в базу добавить объявление и автомобиль, с фотографией
 * а потом к объявлению добавлял фото
 * также у объявления можно и удалить фото
 */
public class PhotoDbTest {
    final static Logger LOGGER = getLogger(UsersDbTest.class);

    private ArrayList<String> urlPhotolist = new ArrayList<>(Arrays.asList("db/Avito-Shema.png"));
    private String jsonCar = "{\"marka\":{\"id\":\"1\"}, \"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
    String jsonAnn = "{\"name\":\"продам машину\", \"author\":{\"id\":\"1\"}}";
    private Announcement announcement = new ServiceAddObjects().addAll(jsonAnn, jsonCar, urlPhotolist);

    private void testAll(BiConsumer<PhotoDb, Announcement> fank) throws JsonProcessingException {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.save(announcement);
            session.getTransaction().commit();
            String jsonCartest = String.format("{\"id\":\"%s\"}", announcement.getCar().getId());
            String jsonAnntest = String.format("{\"id\":\"%s\"}", announcement.getId());
            Announcement annTest = new ServiceAddObjects().addAll(jsonAnntest, jsonCartest, urlPhotolist);
            PhotoDb.getInstance().add(annTest.getCar().getPhoto());
            fank.accept(PhotoDb.getInstance(), announcement);
        } finally {
            SessionFactory sfactory = new Configuration().configure().buildSessionFactory();
            Session ssession = sfactory.openSession();
            ssession.beginTransaction();
            Announcement dele = ssession.get(Announcement.class, announcement.getId());
            ssession.delete(dele);
            ssession.getTransaction().commit();
            sfactory.close();
            ssession.close();
        }
        factory.close();
        session.close();
    }

    @Test
    public void phototest() throws JsonProcessingException {
        testAll((db, ann) -> {
            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            Announcement announcement = session.get(Announcement.class, ann.getId());
            assertThat(announcement.getCar().getPhoto().size() == 2, Is.is(true));
            assertThat(announcement.getCar().getPhoto().get(0).getId() > 0, Is.is(true));
            session.close();
            factory.close();
        });
    }
}