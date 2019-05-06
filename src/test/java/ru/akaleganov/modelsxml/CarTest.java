package ru.akaleganov.modelsxml;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.xerces.impl.dv.util.Base64;
import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.akaleganov.modelsxml.Car;
import ru.akaleganov.modelsxml.Photo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
            Car test = session.get(Car.class, car.getId());
            assertThat(test.getMarka(), Is.is(car.getMarka()));
        });
    }

    //так буду удалять и добавлять авто с фотографиями
    @Test
    public void testPhotoCarDelete() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage image = ImageIO.read(new File("db/Avito-Shema.png"));
        ImageIO.write(image, "png", baos);
        baos.flush();
        String base64String = Base64.encode(baos.toByteArray());
        baos.close();
        byte[] resByteArray = Base64.decode(base64String);
        Photo photo = new Photo();
        photo.setPhoto(resByteArray);

        String jsonCar = "{\"marka\":{\"id\":\"1\"}, \"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
        Car car = new ObjectMapper().readValue(jsonCar, Car.class);
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(photo);
        car.setPhoto(photos);
        car.getPhoto().get(0).setCar(car);
        testfank(session -> {
            session.save(car);
            assertThat(car.getId() != 0, Is.is(true));
            assertThat(car.getPhoto().get(0).getId() != 0, Is.is(true));
            assertThat(car.getPhoto().get(0).getCar().getId() != 0, Is.is(true));
        });
        testfank(session -> {
            session.delete(session.get(Car.class, car.getId()));
        });
    }
}