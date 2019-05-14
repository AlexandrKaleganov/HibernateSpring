package ru.akaleganov.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.apache.xerces.impl.dv.util.Base64;
import ru.akaleganov.modelsxml.Announcement;
import ru.akaleganov.modelsxml.Car;
import ru.akaleganov.modelsxml.Photo;
import ru.akaleganov.modelsxml.Users;

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

    /**
     * класс который будет из строк (json или url )возвращать готовые объекты
     *
     * @param jsonStroka
     * @return
     * @throws IOException
     */
    public Announcement addAnnouncement(String jsonStroka) throws IOException {
        Announcement item = new ObjectMapper().readValue(jsonStroka, Announcement.class);
        item.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        item.setDone(false);
        return item;
    }

    public Car addCar(String jsonStroka) throws IOException {
        return new ObjectMapper().readValue(jsonStroka, Car.class);
    }

    public Users addUser(String json) throws IOException {
        return new ObjectMapper().readValue(json, Users.class);
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
    public Announcement addAll(String jsonann, String jsoncar, ArrayList<String> urlPhoto) throws IOException {
        Announcement ann = this.addAnnouncement(jsonann);
        Car car = this.addCar(jsoncar);
        ArrayList<Photo> photos = (ArrayList<Photo>) this.addPhoto(urlPhoto);
        return this.addAll(ann, car, photos);
    }
}