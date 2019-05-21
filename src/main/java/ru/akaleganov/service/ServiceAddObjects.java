package ru.akaleganov.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.apache.xerces.impl.dv.util.Base64;
import ru.akaleganov.modelsannot.Announcement;
import ru.akaleganov.modelsannot.Car;
import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.modelsannot.Users;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceAddObjects {
    private static final Logger LOGGER = Logger.getLogger(ServiceAddObjects.class);
    private static final ServiceAddObjects INSTANCE = new ServiceAddObjects();

    public static ServiceAddObjects getInstance() {
        return INSTANCE;
    }
    public Announcement addAnnouncement(String jsonStroka) {
        Announcement item = null;
        try {
            item = new ObjectMapper().readValue(jsonStroka, Announcement.class);
            item.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            item.setDone(false);
            return item;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return new Announcement();
        }
    }

    public Car addCar(String jsonStroka) {
        try {
            return new ObjectMapper().readValue(jsonStroka, Car.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return new Car();
        }
    }

    public Users addUser(String json) {
        try {
            return new ObjectMapper().readValue(json, Users.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return new Users();
        }
    }

    public List<Photo> addPhoto(ArrayList<String> urlList) {
        ArrayList<Photo> photos = new ArrayList<>();
        urlList.forEach(url -> {
            if (url != null && url.length() > 1) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedImage image = null;
                String base64String = null;
                try {
                    image = ImageIO.read(new File(url));
                    String formatName = "";
                    for (String temp : url.split("\\.")) {
                        formatName = temp;
                    }
                    ImageIO.write(image, formatName, baos);
                    baos.flush();
                    base64String = Base64.encode(baos.toByteArray());
                    baos.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                byte[] resByteArray = Base64.decode(base64String);
                Photo photo = new Photo();
                photo.setPhoto(resByteArray);
                photos.add(photo);
            }
        });
        return photos;
    }

    /**
     * метод будет объеденять наши объекты
     *
     * @param ann
     * @param car
     * @param photos
     * @return
     */
    public Announcement addAll(Announcement ann, Car car, List<Photo> photos) {
        ann.setCar(car);
        car.setAnnouncement(ann);
        if (photos.size() > 0) {
            car.setPhoto(photos);
            photos.forEach(e -> e.setCar(car));
        }
        return ann;
    }

    /**
     * перегруженный метод для работы с json строками
     *
     * @param jsonann
     * @param jsoncar
     * @param urlPhoto
     * @return
     * @throws IOException
     */
    public Announcement addAll(String jsonann, String jsoncar, ArrayList<String> urlPhoto) {
        Announcement ann = this.addAnnouncement(jsonann);
        Car car = this.addCar(jsoncar);
        ArrayList<Photo> photos = (ArrayList<Photo>) this.addPhoto(urlPhoto);
        return this.addAll(ann, car, photos);
    }
}
