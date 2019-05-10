package ru.akaleganov.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.akaleganov.models.Announcement;
import ru.akaleganov.models.Car;
import ru.akaleganov.models.Marka;
import ru.akaleganov.models.Users;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ServiceAddObjects {


    public Announcement addAnnouncement(String jsonStroka) throws IOException {
        Announcement item = new ObjectMapper().readValue(jsonStroka, Announcement.class);
        ObjectMapper mapper = new ObjectMapper();
        item.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        item.setDone(false);
        return item;
    }

    public Car addCar(String jsonStroka) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStroka, Car.class);
    }
}
