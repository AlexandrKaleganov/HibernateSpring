package ru.akaleganov.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 */
public class Sfactory implements AutoCloseable {
    private final SessionFactory factory;
    private final static Sfactory INSTANCE = new Sfactory();

    public static Sfactory getINSTANCE() {
        return INSTANCE;
    }

    private Sfactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public SessionFactory getFactory() {
        return factory;
    }

    @Override
    public void close() {
        if (!this.factory.isClosed()) {
            factory.close();
        }
    }
}
