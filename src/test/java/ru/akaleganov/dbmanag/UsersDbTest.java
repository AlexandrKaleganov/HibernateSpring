package ru.akaleganov.dbmanag;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.Test;
import ru.akaleganov.modelsannot.Roles;
import ru.akaleganov.modelsannot.Users;
import ru.akaleganov.service.ServiceAddObjects;

import java.util.function.BiConsumer;

import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;


public class UsersDbTest {
    final static Logger LOGGER = getLogger(UsersDbTest.class);

    private String jsonUser = "{\"name\":\"name\", \"login\":\"login2\", \"roles\":{\"id\":\"1\",\"role\":\"ADMIN\"}, \"password\":\"pass\"}";
    String jsonRole = "{\"id\":\"1\", \"role\":\"ADMIN\"}";
    Roles role = RolesDb.getInstance().add(new ServiceAddObjects().addRole(jsonRole));
    Users users = UsersDb.getInstance().add(new ServiceAddObjects().addUser(jsonUser));

    private void testAll(BiConsumer<UsersDb, Users> fank) {
        fank.accept(UsersDb.getInstance(), this.users);
    }

    @Test
    public void add() {
        this.testAll((db, user) -> {
            LOGGER.info("user пришёл в метод = " + user);
            Users users = UsersDb.getInstance().findByLogin(user);
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
    public void edit() {
        this.testAll((db, u) -> {
            u.setName("вася");
            db.edit(u);
            assertThat(db.findByID(u).getName(), Is.is("вася"));
        });
    }

    @Test
    public void findAll() {
        this.testAll((db, u) -> {
            assertThat(db.findAll().size() > 0, Is.is(true));
        });
    }

    @Test
    public void findByID() {
        this.testAll((db, u) -> {
            LOGGER.error(u.getId() + " id =");
            Users users = UsersDb.getInstance().findByLogin(u);
            assertThat(db.findByID(users).getLogin(), Is.is("login2"));
        });
    }


    @Test
    public void findByLoginPass() {
        this.testAll((db, u) -> {
            assertThat(db.findByLoginPass(u).getLogin(), Is.is("login2"));
        });
    }

    @Test
    public void findByLogin() {
        this.testAll((db, u) -> {
            assertThat(db.findByLogin(u).getLogin(), Is.is("login2"));
        });
    }
}