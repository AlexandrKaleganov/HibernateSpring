package ru.akaleganov.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Alexander Kaleganov
 * @since 17.05.19
 * синглтон
 */
public class Sfactory implements AutoCloseable {
    private final SessionFactory factory;
    private final static Sfactory INSTANCE = new Sfactory();

    public static Sfactory getINSTANCE() {
        return INSTANCE;
    }

    public Sfactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public SessionFactory getFactory() {
        return factory;
    }

    @Override
    public void close() throws Exception {
        if (!this.factory.isClosed()) {
            factory.close();
        }
    }
}
