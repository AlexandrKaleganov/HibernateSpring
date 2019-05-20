package ru.akaleganov.dbmanag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.akaleganov.modelsannot.Announcement;
import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.modelsannot.Users;
import ru.akaleganov.service.ServiceAddObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhotoDbTest {
    final static Logger LOGGER = getLogger(UsersDbTest.class);

    private ArrayList<String> urlPhotolist = new ArrayList<>(Arrays.asList("db/Avito-Shema.png"));
    private String jsonCar = "{\"description\":\"1\"}";
    private String jsonAnn = "{\"name\":\"продам машину\", \"author\":{\"id\":\"1\"}}";
    private Announcement announcement = new ServiceAddObjects().addAll(jsonAnn, jsonCar, urlPhotolist);

    private void testAll(BiConsumer<PhotoDb, List<Photo>> fank) throws JsonProcessingException {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.save(announcement);
            System.out.println(announcement);
            session.getTransaction().commit();
            System.out.println(announcement);
            String jsonCartest = String.format("{\"id\":\"%s\"}", announcement.getCar().getId());
            String jsonAnntest = String.format("{\"id\":\"%s\"}", announcement.getId());
            Announcement annTest = new ServiceAddObjects().addAll(jsonAnntest, jsonCartest, urlPhotolist);

            ArrayList<Photo> photolistnew = (ArrayList<Photo>) PhotoDb.getInstance().add(annTest.getCar().getPhoto());
//            fank.accept(PhotoDb.getInstance(), photolistnew);
        } finally {
            session.delete(announcement);
        }
        factory.close();
        session.close();

    }

    @Test
    public void phototest() throws JsonProcessingException {
        testAll((db, list) -> {
            assertThat(list.size() == 2, Is.is(true));
            assertThat(list.get(0).getId() > 0, Is.is(true));
        });
    }

}