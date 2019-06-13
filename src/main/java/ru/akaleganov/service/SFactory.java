package ru.akaleganov.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * создание фабрики сессий сессий
 * @author Alexander Kaleganov
 * @since 14/06/2019
 * <br/>
 * <b>класс содержит поля:</b>
 * @see SFactory#factory
 * @see SFactory#S_FACTORY
 */
public class SFactory implements AutoCloseable {
    /**
     * фабрика сессий {@link SessionFactory}
     */
    private final SessionFactory factory;
    /**
     * синглтон {@link SFactory}
     */
    private final static SFactory S_FACTORY = new SFactory();

    /**
     *
     * @return {@link SFactory#factory}
     */
    public static SFactory getSfactory() {
        return S_FACTORY;
    }

    /**
     * конструктор создаёт фабрику сессий
     */
    private SFactory() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    /**
     *
     * @return получить готовую фабрику сессий {@link SFactory#factory}
     */
    public SessionFactory getFactory() {
        return factory;
    }

    /**
     * закрытие фабрики
     *
     */
    @Override
    public void close() {
        if (!this.factory.isClosed()) {
            factory.close();
        }
    }
}
