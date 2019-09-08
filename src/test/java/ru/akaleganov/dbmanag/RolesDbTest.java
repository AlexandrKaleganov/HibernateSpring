package ru.akaleganov.dbmanag;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.akaleganov.modelsannot.Roles;
import ru.akaleganov.service.ServiceAddObjects;

import static org.hamcrest.MatcherAssert.assertThat;

//import static org.junit.Assert.*;

public class RolesDbTest {

    @Test
    public void findAll() {
        String jsonRole = "{\"id\":\"1\", \"role\":\"ADMIN\"}";
        Roles role = RolesDb.getInstance().add(new ServiceAddObjects().addRole(jsonRole));
        assertThat(RolesDb.getInstance().findByID(role).getRole(), Is.is("ADMIN"));
    }
}