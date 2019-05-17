package ru.akaleganov.service;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SfactoryTest {
    @Test
    public void sessionTest() {
        Sfactory sfactory = Sfactory.getINSTANCE();
        assertFalse(sfactory.getFactory().isClosed());
        try {
            sfactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(sfactory.getFactory().isClosed());
    }

}