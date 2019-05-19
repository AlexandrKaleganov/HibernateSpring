package ru.akaleganov.dbmanag;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.akaleganov.modelsannot.Transmission;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;


public class TransmissionDbTest {
    @Test
    public void allTransmission() {
        ArrayList<Transmission> actual = (ArrayList<Transmission>) TransmissionDb.getInstance().findAll();
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("АВТОМАТ",
                "МЕХАНИЧ",
                "ВАРИАТОР",
                "РОБОТ"));
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i).getName(), Is.is(expected.get(i)));
        }
    }
}