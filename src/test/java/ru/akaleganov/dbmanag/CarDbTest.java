package ru.akaleganov.dbmanag;

import org.junit.Test;
import ru.akaleganov.modelsannot.*;
import ru.akaleganov.service.ServiceAddObjects;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class CarDbTest {
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
    private String jsonCar = "{\"id\":\"1\",\"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"1\"}, \"yar\":\"1899\"}";
    private String jsonann = "{\"name\":\"продам машину\", \"author\":{\"id\":\"1\"}}";
    Car rar = CarDb.getInstance().add(new ServiceAddObjects().addCar(jsonCar));
    private Announcement ann = new ServiceAddObjects().addAll(jsonann, jsonCar, new ArrayList<>());
    private Announcement announcement = AnnouncementDb.getInstance().add(ann);
    //        ArrayList<Photo> photos = (ArrayList<Photo>)new PhotoDb().add(new ServiceAddObjects().addPhoto(urlList));
//   фирмируем объек End

    @Test
    public void addTest() {
        Car carDat = AnnouncementDb.getInstance().findByID(announcement).getCar();
        assertTrue(carDat.getId() > 0);
    }

    @Test
    public void edit() {
        Car carDat = AnnouncementDb.getInstance().findByID(announcement).getCar();
        carDat.setYar(1999);
        Car edit = CarDb.getInstance().edit(carDat);
        assertTrue(AnnouncementDb.getInstance().findByID(announcement).getCar().getYar() == 1999);
    }
}