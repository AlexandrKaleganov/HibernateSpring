package ru.akaleganov.dbmanag;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.akaleganov.modelsannot.Roles;
import ru.akaleganov.service.Sfactory;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

//import static org.junit.Assert.*;

public class RolesDbTest {

    @Test
    public void findAll() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("ADMIN", "USER"));
        ArrayList<Roles> roles = (ArrayList<Roles>) new RolesDb().findAll();
        for (int i = 0; i < roles.size(); i++) {
            assertThat(roles.get(i).getRole(), Is.is(expected.get(i)));
        }
        try {
            Sfactory.getINSTANCE().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}