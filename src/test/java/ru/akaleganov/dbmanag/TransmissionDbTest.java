package ru.akaleganov.dbmanag;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.akaleganov.modelsannot.Transmission;
import ru.akaleganov.service.ServiceAddObjects;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;


public class TransmissionDbTest {
    @Test
    public void allTransmission() {
         String jsonTransmission = "{\"id\":\"1\", \"name\":\"РОБОТ\"}";
        Transmission transmission = TransmissionDb.getInstance().
                add(new ServiceAddObjects().addTransmission(jsonTransmission));
        ArrayList<Transmission> actual = (ArrayList<Transmission>) TransmissionDb.getInstance().findAll();
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(
                "РОБОТ"));
            assertThat(actual.get(0).getName(), Is.is(expected.get(0)));
    }
}