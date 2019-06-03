package ru.akaleganov.service;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.akaleganov.modelsannot.Announcement;
import ru.akaleganov.modelsannot.Roles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

public class ServiceAddObjectsTest {
    @Test
    public void testAddac() {
        String jsonCar = "{ \"description\":\"описание\", \"model\":{\"id\":\"1\",  \"marka\":{\"id\":\"1\"}}, \"transmission\":{\"id\":\"4\"}, \"yar\":\"1999\"}";
        ArrayList<String> urlList = new ArrayList<>(Arrays.asList("db/Avito-Shema.png"));
        String jsonann = "{\"name\":\"продам машину\", \"author\":{\"id\":\"1\"}}";
        //в классе ServiceAddObjects все объекты объеденятся и запакуются в один готовый объект для добавления в базу
        Announcement ann = new ServiceAddObjects().addAll(jsonann, jsonCar, urlList);
        assertThat(ann.getName(), Is.is("продам машину"));
        assertThat(ann.getCar().getModel().getMarka().getId(), Is.is(1));
        assertThat(ann.getCar().getPhoto().get(0).getPhoto().length > 0, Is.is(true));
        assertThat(ann.getAuthor().getId(), Is.is(1));
    }

    @Test
    public void testFormat() {
        String url = "db/Avito-Shema.png";
        String expected = "";
        for (String temp : url.split("\\.")) {
            expected = temp;
        }
        assertThat(expected, Is.is("png"));
    }

    @Test
    public void testFormatRoles() {
        String json = "{\"id\":\"0\"" + "}";
        Roles role = new ServiceAddObjects().addRole(json);
        assertThat(role.getId(), Is.is(0));
    }
}