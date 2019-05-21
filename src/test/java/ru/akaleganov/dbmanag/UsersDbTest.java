package ru.akaleganov.dbmanag;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.Test;
import ru.akaleganov.modelsannot.Users;
import ru.akaleganov.service.ServiceAddObjects;
import ru.akaleganov.service.Sfactory;

import java.util.function.BiConsumer;

import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;


public class UsersDbTest {
    final static Logger LOGGER = getLogger(UsersDbTest.class);

    private String jsonUser = "{\"name\":\"name\", \"login\":\"login\", \"roles\":{\"id\":\"1\",\"role\":\"ADMIN\"}, \"password\":\"pass\"}";

    private void testAll(BiConsumer<UsersDb, Users> fank) {

        Users users =  UsersDb.getInstance().add(new ServiceAddObjects().addUser(jsonUser));
        try {
            fank.accept(UsersDb.getInstance(), users);
        } finally {
            UsersDb.getInstance().delete(users);
        }
    }

    @Test
    public void add() {
        Users users = UsersDb.getInstance().add(new ServiceAddObjects().addUser(jsonUser));
        assertThat(users.getId() > 0, Is.is(true));
        System.out.println(users);
        UsersDb.getInstance().delete(users);
    }

    @Test
    public void delete() {
        this.testAll((db, u) -> {
            db.delete(u);
            assertThat(db.findByID(u), Is.is((Users) null));
        });
    }

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
            assertThat(db.findAll().get(0).getName(), Is.is("root"));
        });
    }

    @Test
    public void findByID() {
        this.testAll((db, u) -> {
            assertThat(db.findByID(u).getName(), Is.is("name"));
        });
    }

    @Test
    public void findByName() {
        this.testAll((db, u) -> {
            assertThat(db.findByName(u).get(0).getName(), Is.is("name"));
        });
    }

    @Test
    public void findByLoginPass() {
        this.testAll((db, u) -> {
            assertThat(db.findByLoginPass(u).getName(), Is.is("name"));
        });
    }
    @Test
    public void findByLogin() {
        this.testAll((db, u) -> {
            assertThat(db.findByLogin(u).getName(), Is.is("name"));
        });
    }
}