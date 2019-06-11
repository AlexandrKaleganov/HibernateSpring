package ru.akaleganov.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * тестирование класса Получения фабрики сессий закрытие фабрики
 */
public class SFactoryTest {
    @Test
    public void isClosedTest() throws Exception {
        SFactory factory = new SFactory();
        assertFalse(factory.getFactory().isClosed());
        factory.close();
        assertTrue(factory.getFactory().isClosed());
    }

}