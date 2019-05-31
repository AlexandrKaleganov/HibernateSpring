package ru.akaleganov.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ru.akaleganov.modelsannot.Announcement;
import ru.akaleganov.modelsannot.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class DispatchTest {

@Test
    public void userList() {
//    ArrayList<Users> liist = Dispatch.getInstance().access("getListUser", Optional.of(new Users()));;
//    assertTrue(liist.get(0).getName().contains("root"));
}
//    @Test
//    public void anList() throws IOException {
//    String json  = "{\"id\":\"0\"" + "}";
//    Announcement an = ServiceAddObjects.getInstance().addAnnouncement(json);
//        System.out.println(an);
//        Dispatch disp = mock(Dispatch.class);
//        ArrayList<Announcement> liist = new Dispatch().init().access("findallan", Optional.of(an));
//        System.out.println(liist);
//        assertTrue(liist.get(0).getId() == 123);
//    }
}