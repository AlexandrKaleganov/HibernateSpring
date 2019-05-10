package ru.akaleganov.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.xerces.impl.dv.util.Base64;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class CarTest {
    private void testfank(Consumer<Session> test) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        test.accept(session);
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    @Test
    public void testCar() throws IOException {
        String jsonCar = "{\"marka\":{\"id\":\"1\"}, \"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
        Car car = new ObjectMapper().readValue(jsonCar, Car.class);
        System.out.println(car);
        testfank(session -> {
            session.save(car);
        });
    }
    @Test
    public void testPhoto() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage image = ImageIO.read(new File("db/Avito-Shema.png"));
        ImageIO.write(image, "png", baos);
        baos.flush();
        String base64String = Base64.encode(baos.toByteArray());
        baos.close();

        // декодируем полученную строку в массив байт
        byte[] resByteArray = Base64.decode(base64String);
        Photo photo = new Photo();
        photo.setPhoto(resByteArray);
        System.out.println(photo);
        String jsonCar = "{\"marka\":{\"id\":\"1\"}, \"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
        Car car = new ObjectMapper().readValue(jsonCar, Car.class);
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(photo);
        car.setPhoto(photos);
        testfank(session -> {
            session.save(car);
        });
    }
}