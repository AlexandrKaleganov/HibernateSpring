package ru.akaleganov.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * класс возвращает фабрику сессий и закрывает фабрику
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 * @see Sfactory#factory
 */
public class Sfactory implements AutoCloseable {
    /**
     * фабрика сессий
     */
    private final SessionFactory factory;
    private final static Sfactory INSTANCE = new Sfactory();

    /**
     *
     * @return {@link Sfactory}
     */
    public static Sfactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * конструктор сразу создаёт фабрику сессий
     */
    private Sfactory() {
        factory = new Configuration().configure().buildSessionFactory();
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
