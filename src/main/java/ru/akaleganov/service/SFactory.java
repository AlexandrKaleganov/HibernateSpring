package ru.akaleganov.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SFactory implements AutoCloseable {
    private final SessionFactory factory;

    private final static SFactory S_FACTORY = new SFactory();

    public static SFactory getSfactory() {
        return S_FACTORY;
    }

    public SFactory() {
        this.factory = new Configuration().configure().buildSessionFactory();
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
