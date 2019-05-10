package ru.akaleganov.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.akaleganov.models.Announcement;
import ru.akaleganov.models.Car;
import ru.akaleganov.models.Marka;
import ru.akaleganov.models.Users;

import java.io.IOException;
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

    public Car addCar(String jsonStroka) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStroka, Car.class);
    }
}
