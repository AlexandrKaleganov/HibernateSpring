package ru.akaleganov.dbmanag;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.config.SpringTestConfig;
import ru.akaleganov.domain.*;
import ru.akaleganov.service.*;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import static org.apache.log4j.Logger.getLogger;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("тестирование: сервис объявления")
@SpringBootTest(classes = SpringTestConfig.class)
@TestPropertySource(locations = "classpath:application-h2.properties")
public class AnnouncementServiceTest {
    private final static Logger LOGGER = getLogger(AnnouncementServiceTest.class);
    //    формируем объект Start
    @Autowired
    private RolesService rolesService;
    @Autowired
    private MarkService markService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private CarService carService;
    @Autowired
    private TransmissionService transmissionService;
    @Autowired
    private UsersService usersService;

    @Autowired
    private AnnouncementService announcementService;


    private String jsonRole = "{\"id\":\"1\", \"role\":\"ADMIN\"}";
    private String jsonUSer = "{\"name\":\"name\", \"login\":\"login\", \"roles\":{\"id\":\"1\",\"role\":\"ADMIN\"}, \"password\":\"pass\"}";
    private String jsonMark = "{\"id\":\"1\", \"name\":\"Toyota\"}";
    private String jsonTransmission = "{\"id\":\"1\", \"name\":\"РОБОТ\"}";
    private String jsonModel = "{\"id\":\"1\", \"name\":\"RAV\", \"mark\":{\"id\":\"1\"}}";
    private String jsonCar = "{\"id\":\"\",\"model\":{\"id\":\"1\"},\"transmission\":{\"id\":\"1\"}, \"yar\":\"1999\"}";
    private String jsonann = "{\"name\":\"продам машину\", \"author\":{\"id\":\"1\"}}";
    //        ArrayList<Photo> photos = (ArrayList<Photo>)new PhotoDb().add(new ServiceAddObjects().addPhoto(urlList));
    private Announcement ann = new ServiceAddObjects().addAll(jsonann, jsonCar, new ArrayList<>());
//   фирмируем объек End

    /**
     * интерфейс для проведения тестов
     *
     * @param fank функция для тестирования
     */
    private void testAll(BiConsumer<AnnouncementService, Announcement> fank) {
        Roles role = this.rolesService.add(new ServiceAddObjects().addRole(jsonRole));
        Users users = this.usersService.add(new ServiceAddObjects().addUser(jsonUSer));
        Transmission transmission = this.transmissionService.
                add(new ServiceAddObjects().addTransmission(jsonTransmission));
        Mark mark = this.markService.add(new ServiceAddObjects().addMark(jsonMark));
        Model model = this.modelService.add(new ServiceAddObjects().addModel(jsonModel));
        Car rar = this.carService.add(new ServiceAddObjects().addCar(jsonCar));
        Announcement announcement = this.announcementService.add(ann);

        fank.accept(this.announcementService, announcement);
    }

    @Test
    @DisplayName("тестирование: добавление объявления")
    public void add() {
        this.testAll((db, an) -> {
            assertNotNull(this.announcementService.findByID(an));
        });
    }

    @Test
    @DisplayName("тестирование: редактирование объявления")
    public void edit() {
        testAll((db, ann) -> {
            ann.getCar().setDescription("изменено описание");
            ann.setName("изменено");
            db.edit(ann);
            Announcement expected = this.announcementService.findByID(ann);
            LOGGER.error(expected);
            assertTrue(expected.getName().contains("изменено"));
            assertTrue(expected.getCar().getDescription().contains("изменено описание"));
        });
    }

    @Test
    @DisplayName("тестирование: получить список объявления")
    public void findAll() {
        testAll((db, ann) -> {
            assertTrue(db.findAll().size() > 0);
        });
    }

    @Test
    @DisplayName("тестирование: поиск объявления по id")
    public void findByID() {
        testAll((db, ann) -> {
            Announcement expected = db.findByID(ann);
            assertTrue(expected.getId() > 0);
        });
    }

    @Test
    @DisplayName("тестирование: поиск объявления по имени")
    public void findByName() {
        testAll((db, ann) -> {
            assertTrue(db.findByName(ann).get(0).getName().contains("продам машину"));
        });
    }
}