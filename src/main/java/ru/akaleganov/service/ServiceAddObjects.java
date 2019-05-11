package ru.akaleganov.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.akaleganov.modelsxml.Announcement;
import ru.akaleganov.modelsxml.Car;
import ru.akaleganov.modelsxml.Photo;
import ru.akaleganov.modelsxml.Users;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ServiceAddObjects {
    private static final Logger LOGGER = Logger.getLogger(ServiceAddObjects.class);

    /**
     * класс который будет из json  возвращать готовые объекты
     *
     * @param jsonStroka
     * @return
     * @throws IOException
     */
    Announcement addAnnouncement(String jsonStroka) throws IOException {
        Announcement item = new ObjectMapper().readValue(jsonStroka, Announcement.class);
        item.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        item.setDone(false);
        return item;
    }

    Car addCar(String jsonStroka) throws IOException {
        return new ObjectMapper().readValue(jsonStroka, Car.class);
    }

    public Users addUser(String json) throws IOException {
        return new ObjectMapper().readValue(json, Users.class);
    }

    public List<Photo> addPhoto(String json) throws IOException {
        return Arrays.asList(new ObjectMapper().readValue(json, Photo[].class));
    }
}
