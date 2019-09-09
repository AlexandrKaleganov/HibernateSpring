package ru.akaleganov.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Closeable;

/**
 * класс возвращает фабрику сессий и закрывает фабрику
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 * @see Sfactory#factory
 */

public class Sfactory implements Closeable {
    /**
     * фабрика сессий
     */
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private final static Sfactory INSTANCE = new Sfactory();

    /**
     *
     * @return {@link Sfactory}
     */
    public static Sfactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     *
     * @return {@link Sfactory#factory}
     */
    public SessionFactory getFactory() {
        return factory;
    }

    /**
     * метода закрывает фабрику сессий
     */
    @Override
    public void close() {
        if (!this.factory.isClosed()) {
            factory.close();
        }
    }
}
