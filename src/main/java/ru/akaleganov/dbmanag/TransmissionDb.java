package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Transmission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
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
        ArrayList<Transmission>  rsl = (ArrayList<Transmission>)openSession(session -> session.createQuery("from Transmission").list());
        if (rsl == null) {
            rsl = new ArrayList<>();
        }
        return rsl;
    }

    @Override
    public Transmission findByID(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public List<Transmission> findByName(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public Transmission findByLoginPass(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public Transmission findByLogin(Transmission transmission) {
        error();
        return null;
    }
}
