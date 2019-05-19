package ru.akaleganov.dbmanag;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.akaleganov.modelsannot.Marka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class MarkaDbTest {

    @Test
    public void findAll() {
        HashMap<String, ArrayList<String>> mapExpected = new HashMap<String, ArrayList<String>>();
        mapExpected.put("Toyota", new ArrayList<>(Arrays.asList("RAV",
                "Corolla",
                "Corolla Runx",
                "Corolla Alex",
                "Filder",
                "Carina")));
        mapExpected.put("Nissan", new ArrayList<>(Arrays.asList("Sunny",
                "Bluebird Sylphy",
                "Qashqai",
                "Skyline")));
        mapExpected.put("Honda", new ArrayList<>(Arrays.asList("Civic",
                "Stream",
                "CR-V")));
        ArrayList<Marka> markaList = (ArrayList<Marka>) MarkaDb.getInstance().findAll();
        markaList.forEach(e -> {
            assertThat(mapExpected.containsKey(e.getName()), Is.is(true));
            var expected = mapExpected.get(e.getName());
            for (int i = 0; i < expected.size(); i++) {
                assertThat(expected.get(i), Is.is(e.getModels().get(i).getName()));
            }
        });
    }

    @Test
    public void findByID() {
        assertThat(MarkaDb.getInstance().findByID(new Marka(1)).getName(), Is.is("Toyota"));
    }
}