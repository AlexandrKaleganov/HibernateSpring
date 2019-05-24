package ru.akaleganov.service;

import org.junit.Test;
import ru.akaleganov.modelsannot.Users;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DispatchTest {
@Test
    public void userist() {
    ArrayList<Users> userist = Dispatch.getInstance().access("getListUser", null);;
    assertTrue(userist.get(0).getName().contains("root"));
}
}