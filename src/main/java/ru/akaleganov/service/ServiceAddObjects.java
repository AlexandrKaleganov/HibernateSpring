package ru.akaleganov.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.impl.dv.util.Base64;
import ru.akaleganov.modelsannot.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceAddObjects {
    private static final Logger LOGGER = Logger.getLogger(ServiceAddObjects.class);
    private static final ServiceAddObjects INSTANCE = new ServiceAddObjects();

    public static ServiceAddObjects getInstance() {
        return INSTANCE;
    }

    private Announcement addAnnouncement(String jsonStroka) {
        Announcement item;
        try {
            item = new ObjectMapper().readValue(jsonStroka, Announcement.class);
            item.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            return item;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return new Announcement();
        }
    }

    public Car addCar(String json) {
        return this.addModel(json, j -> new ObjectMapper().readValue(j, Car.class));
    }

    public Users addUser(String json) {
        return this.addModel(json, j -> new ObjectMapper().readValue(j, Users.class));
    }

    public Roles addRole(String json) {
        return this.addModel(json, j -> new ObjectMapper().readValue(j, Roles.class));
    }

    public Transmission addTransmission(String json) {
        return this.addModel(json, j -> new ObjectMapper().readValue(j, Transmission.class));
    }

    public Marka addMarka(String json) {
        return this.addModel(json, j -> new ObjectMapper().readValue(j, Marka.class));
    }

    private List<Photo> addPhoto(ArrayList<String> urlList) {
        ArrayList<Photo> photos = new ArrayList<>();
        urlList.forEach(url -> {
            if (url != null && url.length() > 1) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedImage image;
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
     * метод для получения Обекта вото из входящего файла
     *
     * @param img {@link File} файл фотографии jpg / png
     * @return {@link Photo}
     */
    public Photo addPhoto(File img) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage image;
        String base64String = null;
        try {
            image = ImageIO.read(img);
            String formatName = "";
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
        return photo;
    }

    /**
     * получение массива байт из инпут стреим
     *
     * @param img {@link InputStream}  входящего файла
     * @return {@link Photo}
     */
    public ArrayList<Photo> addPhoto(ArrayList<Photo> photos, InputStream img) {
        byte[] resByteArray = new byte[]{};
        try {
            resByteArray = IOUtils.toByteArray(img);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        Photo photo = new Photo();
        photo.setPhoto(resByteArray);
        photos.add(photo);
        return photos;
    }

    /**
     * метод будет объеденять наши объекты
     *
     * @param ann    {@link Announcement} объект обявление
     * @param car    {@link Car} объект автомобиль который создаётся уникальный для каждого объявления
     * @param photos {@link Photo} лист фотографий
     * @return {@link Announcement} из входящих параметров собирается объявление
     */
    private Announcement addAll(Announcement ann, Car car, List<Photo> photos) {
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
     * @param jsonann  объект объявле объявления в формате JSON
     * @param jsoncar  строка  в формате JSON
     * @param urlPhoto список ссылок на фотографии
     * @return {@link Announcement}
     */

    public Announcement addAll(String jsonann, String jsoncar, ArrayList<String> urlPhoto) {
        Announcement ann = this.addAnnouncement(jsonann);
        Car car = this.addCar(jsoncar);
        ArrayList<Photo> photos = (ArrayList<Photo>) this.addPhoto(urlPhoto);
        return this.addAll(ann, car, photos);
    }
    /**
     * перегруженный метод для работы с json строками
     *
     * @param jsonann  объект объявле объявления в формате JSON
     * @param jsoncar  строка  в формате JSON
     * @param photos список с готовыми фотографиями
     * @return {@link Announcement}
     */
    public Announcement addAllObject(String jsonann, String jsoncar, ArrayList<Photo> photos) {
        Announcement ann = this.addAnnouncement(jsonann);
        Car car = this.addCar(jsoncar);
        return this.addAll(ann, car, photos);
    }
    /**
     * рефактор try catch
     *
     * @param json   прилетает json строка, которую мы будем конвертировать в объект
     * @param mapper {@link FankEx} будет принимать json строку и возвращать объект
     * @param <E>    объект, который мы получем после конвертации json строрки в объект
     * @return обект полученный после конвертации строки в объект
     * @author Alexander Kaleganov
     */
    private <E> E addModel(String json, FankEx<String, E> mapper) {
        E rsl = null;
        try {
            rsl = mapper.submit(json);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rsl;
    }

    /**
     * очистка листа с фотографиями из сессии
     * @param photos ArrayList {@link Photo}
     */
    public void clearListSession(ArrayList<Photo> photos) {
        photos.clear();
    }
    /**
     * удаление одной фотографии из сессии
     * @param photos ArrayList {@link Photo}
     */
    public void deleteOnePhoto(ArrayList<Photo> photos, int index) {
        if (photos.size() > index) {
            photos.remove(index);
        }
    }
}
