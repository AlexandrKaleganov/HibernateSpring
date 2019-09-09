package ru.akaleganov.dbmanag;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.akaleganov.modelsannot.Marka;
import ru.akaleganov.service.ServiceAddObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class MarkaDbTest {
    private String jsonMarka = "{\"id\":\"1\", \"name\":\"Toyota\"}";
    Marka marka = MarkaDb.getInstance().add(new ServiceAddObjects().addMarka(jsonMarka));

    @Test
    public void findAll() {
assertTrue(MarkaDb.getInstance().findAll().size() > 0);
    }

    @Test
    public void findByID() {
        assertThat(MarkaDb.getInstance().findByID(marka).getName(), Is.is("Toyota"));
    }
}