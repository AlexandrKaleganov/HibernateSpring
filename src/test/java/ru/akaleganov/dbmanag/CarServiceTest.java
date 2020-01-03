package ru.akaleganov.dbmanag;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.config.SpringTestConfig;
import ru.akaleganov.domain.*;
import ru.akaleganov.service.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("тестирование: сервис автомобилей")
@SpringBootTest(classes = SpringTestConfig.class)
@TestPropertySource(locations = "classpath:application-h2.properties")
public class CarServiceTest {
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private MarkService markService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private CarService carService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private TransmissionService transmissionService;

    //    формируем объект Start
    private String jsonRole = "{\"id\":\"1\", \"role\":\"ADMIN\"}";
    private String jsonUSer = "{\"name\":\"name\", \"login\":\"login\", \"roles\":{\"id\":\"1\",\"role\":\"ADMIN\"}, \"password\":\"pass\"}";
    private String jsonMark = "{\"id\":\"1\", \"name\":\"Toyota\"}";
    private String jsonTransmission = "{\"id\":\"1\", \"name\":\"РОБОТ\"}";
    private String jsonModel = "{\"id\":\"1\", \"name\":\"RAV\", \"mark\":{\"id\":\"1\"}}";


    private String jsonCar = "{\"id\":\"\",\"model\":{\"id\":\"1\"}, \"transmission\":{\"id\":\"1\"}, \"yar\":\"1899\"}";
    private String jsonann = "{\"name\":\"продам машину\", \"author\":{\"id\":\"1\"}}";
    private Announcement ann = new ServiceAddObjects().addAll(jsonann, jsonCar, new ArrayList<>());
    //        ArrayList<Photo> photos = (ArrayList<Photo>)new PhotoDb().add(new ServiceAddObjects().addPhoto(urlList));
//   фирмируем объек End

    @Test
    @DisplayName("тестирование: добавлене объекта автомобиль в бд")
    public void addTest() {
        Transmission transmission = this.transmissionService.
                add(new ServiceAddObjects().addTransmission(jsonTransmission));
        Mark mark = this.markService.add(new ServiceAddObjects().addMark(jsonMark));
        Model model = this.modelService.add(new ServiceAddObjects().addModel(jsonModel));
        Roles role = this.rolesService.add(new ServiceAddObjects().addRole(jsonRole));
        Users users = this.usersService.add(new ServiceAddObjects().addUser(jsonUSer));
        Announcement announcement = this.announcementService.add(ann);
        Car carDat = this.announcementService.findByID(announcement).getCar();
        assertTrue(carDat.getId() > 0);
    }

    @Test
    @DisplayName("тестирование: редактирование объекта автомобиль")
    public void edit() {
        Transmission transmission = this.transmissionService.
                add(new ServiceAddObjects().addTransmission(jsonTransmission));
        Mark mark = this.markService.add(new ServiceAddObjects().addMark(jsonMark));
        Model model = this.modelService.add(new ServiceAddObjects().addModel(jsonModel));
        Roles role = this.rolesService.add(new ServiceAddObjects().addRole(jsonRole));
        Users users = this.usersService.add(new ServiceAddObjects().addUser(jsonUSer));
        Car rar = this.carService.add(new ServiceAddObjects().addCar(jsonCar));
        Announcement announcement = this.announcementService.add(ann);
        Car carDat = this.announcementService.findByID(announcement).getCar();
        carDat.setYar(1999);
        Car edit = this.carService.edit(carDat);
        assertTrue(this.announcementService.findByID(announcement).getCar().getYar() == 1999);
    }
}