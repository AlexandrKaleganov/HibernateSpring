package ru.akaleganov.dbmanag;

import org.apache.log4j.Logger;
import org.junit.Test;
import ru.akaleganov.modelsannot.*;
import ru.akaleganov.service.ServiceAddObjects;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import static org.apache.log4j.Logger.getLogger;
import static org.junit.Assert.assertTrue;

public class AnnouncementDbTest {
    private final static Logger LOGGER = getLogger(AnnouncementDbTest.class);
    //    формируем объект Start
    private String jsonRole = "{\"id\":\"1\", \"role\":\"ADMIN\"}";
    private String jsonUSer = "{\"name\":\"name\", \"login\":\"login\", \"roles\":{\"id\":\"1\",\"role\":\"ADMIN\"}, \"password\":\"pass\"}";
    private String jsonMarka = "{\"id\":\"1\", \"name\":\"Toyota\"}";
    private String jsonTransmission = "{\"id\":\"1\", \"name\":\"РОБОТ\"}";
    private String jsonModel = "{\"id\":\"1\", \"name\":\"RAV\", \"marka\":{\"id\":\"1\"}}";
    Roles role = RolesDb.getInstance().add(new ServiceAddObjects().addRole(jsonRole));
    Users users = UsersDb.getInstance().add(new ServiceAddObjects().addUser(jsonUSer));
    Transmission transmission = TransmissionDb.getInstance().
            add(new ServiceAddObjects().addTransmission(jsonTransmission));
    Marka marka = MarkaDb.getInstance().add(new ServiceAddObjects().addMarka(jsonMarka));
    Model model = ModelDb.getInstance().add(new ServiceAddObjects().addModel(jsonModel));
    private String jsonCar = "{\"id\":\"1\",\"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"1\"}, \"yar\":\"1999\"}";
    private String jsonann = "{\"name\":\"продам машину\", \"author\":{\"id\":\"1\"}}";
    Car rar = CarDb.getInstance().add(new ServiceAddObjects().addCar(jsonCar));
    //        ArrayList<Photo> photos = (ArrayList<Photo>)new PhotoDb().add(new ServiceAddObjects().addPhoto(urlList));
    private Announcement ann = new ServiceAddObjects().addAll(jsonann, jsonCar, new ArrayList<>());
    private Announcement announcement = AnnouncementDb.getInstance().add(ann);
//   фирмируем объек End

    /**
     * интерфейс для проведения тестов
     *
     * @param fank функция для тестирования
     */
    private void testAll(BiConsumer<AnnouncementDb, Announcement> fank) {

        fank.accept(AnnouncementDb.getInstance(), announcement);
    }

    @Test
    public void add() {
        testAll((db, ann) -> {
            assertTrue(0 < ann.getId());
        });
    }

    @Test
    public void edit() {
        testAll((db, ann) -> {
            ann.getCar().setDescription("изменено описание");
            ann.setName("изменено");
            db.edit(ann);
            Announcement expected = db.findByID(ann);
            assertTrue(expected.getName().contains("изменено"));
            assertTrue(expected.getCar().getDescription().contains("изменено описание"));
        });
    }

    @Test
    public void findAll() {
        testAll((db, ann) -> {
            assertTrue(db.findAll().size() > 0);
        });
    }

    @Test
    public void findByID() {
        testAll((db, ann) -> {
            Announcement expected = db.findByID(ann);
            assertTrue(expected.getId() > 0);
        });
    }

    @Test
    public void findByName() {
        testAll((db, ann) -> {
            assertTrue(db.findByName(ann).get(0).getName().contains("продам машину"));
        });
    }
}