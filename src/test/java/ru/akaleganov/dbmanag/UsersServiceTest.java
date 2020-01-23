package ru.akaleganov.dbmanag;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.config.SpringTestConfig;
import ru.akaleganov.domain.Roles;
import ru.akaleganov.domain.Users;
import ru.akaleganov.service.RolesService;
import ru.akaleganov.service.ServiceAddObjects;
import ru.akaleganov.service.UsersService;

import java.util.function.BiConsumer;

import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тестирование: сервис  пользователи")
@SpringBootTest(classes = SpringTestConfig.class)
@TestPropertySource(locations = "classpath:application-h2.properties")
public class UsersServiceTest {
    private final static Logger LOGGER = getLogger(UsersServiceTest.class);
    @Autowired
    private RolesService rolesService;
    @Autowired
    private UsersService usersService;

    private String jsonUser = "{\"name\":\"name\", \"login\":\"login2\", \"roles\":{\"id\":\"1\",\"role\":\"ADMIN\"}, \"password\":\"pass\"}";
    private String jsonRole = "{\"id\":\"1\", \"role\":\"ADMIN\"}";

    private void testAll(BiConsumer<UsersService, Users> fank) {
        Roles role = this.rolesService.add(new ServiceAddObjects().addRole(jsonRole));
        Users users = this.usersService.add(new ServiceAddObjects().addUser(jsonUser));

        fank.accept(this.usersService, users);
    }

    @Test
    @DisplayName("тестирование: добавить пользователя в бд")
    public void add() {
        this.testAll((db, user) -> {
            LOGGER.info("user пришёл в метод = " + user);
            Users users = this.usersService.findByLogin(user);
            LOGGER.info("users = " + users);
            assertThat(users.getLogin(), Is.is("login2"));
        });
    }

//    @Test
//    public void delete() {
//        this.testAll((db, u) -> {
//            db.delete(u);
//            assertThat(db.findByID(u).getId(), Is.is(0));
//        });
//    }

    @Test
    @DisplayName("тестирование: удалить редактирвание пользователя")
    public void edit() {
        this.testAll((db, u) -> {
            Users users = this.usersService.add(new ServiceAddObjects().addUser("{\"name\":\"name\", \"login\":\"login100501\", \"roles\":{\"id\":\"1\",\"role\":\"ADMIN\"}, \"password\":\"pass\"}"));
            Users use = this.usersService.findByID(users);
            use.setName("вася");
            db.edit(use);
            assertThat(db.findByID(use).getName(), Is.is("вася"));
        });
    }

    @Test
    @DisplayName("тестирование: получить список пользователей")
    public void findAll() {
        this.testAll((db, u) -> {
            assertThat(db.findAll().size() > 0, Is.is(true));
        });
    }

    @Test
    @DisplayName("тестирование: получить пользователя по id")
    public void findByID() {
        this.testAll((db, u) -> {
            LOGGER.error(u.getId() + " id =");
            Users users = this.usersService.findByLogin(u);
            assertThat(db.findByID(users).getLogin(), Is.is("login2"));
        });
    }

    @Test
    @DisplayName("тестирование: получить пользователя логину")
    public void findByLogin() {
        this.testAll((db, u) -> {
            Users users = this.usersService.add(new ServiceAddObjects().addUser("{\"name\":\"name\", \"login\":\"login100500\", \"roles\":{\"id\":\"1\",\"role\":\"ADMIN\"}, \"password\":\"pass\"}"));
            assertThat(db.findByLogin(users).getLogin(), Is.is("login100500"));
        });
    }
}