package ru.akaleganov.service;

import ru.akaleganov.models.Announcement;
import ru.akaleganov.models.Car;
import ru.akaleganov.models.Marka;
import ru.akaleganov.models.Users;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ServiceAddandGet {


    public Announcement addAnnouncement(String name, Car car, Users user) {
        Announcement item = new Announcement();
        item.setName(name);
        item.setCar(car);
        item.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        item.setUsers(user);
        item.setDone(false);
        return item;
    }

//    public Car addCar(String marka, String model, String tra, String yar) {
//        Car rsl = new Car();
//        rsl.setMarka(new Marka(0, marka));
//        rsl.setModel(model);
//        rsl.setTransmission(tra);
//        rsl.setYar(yar);
//    }
}
