package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Transmission;

import java.util.List;

public class TransmissionDb implements Store<Transmission> {
    private static final TransmissionDb INSTANCE = new TransmissionDb();

    public static TransmissionDb getInstance() {
        return INSTANCE;
    }
    @Override
    public Transmission add(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public Transmission delete(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public Transmission edit(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public List<Transmission> findAll() {
        return openSession(session -> session.createQuery("from Transmission").list());
    }

    @Override
    public Transmission findByID(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public Transmission findByName(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public Transmission findByLogin(Transmission transmission) {
        error();
        return null;
    }
}
