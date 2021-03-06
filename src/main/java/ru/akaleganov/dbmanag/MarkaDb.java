package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Marka;

import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
public class MarkaDb implements Store<Marka> {
    private static final MarkaDb INSTANCE = new MarkaDb();

    public static MarkaDb getInstance() {
        return INSTANCE;
    }

    @Override
    public Marka add(Marka marka) {
        return openSession(session -> {
            session.save(marka);
            return session.load(Marka.class, marka.getId());
        });
    }

    @Override
    public Marka delete(Marka marka) {
        error();
        return null;
    }

    @Override
    public Marka edit(Marka marka) {
        error();
        return null;
    }

    @Override
    public List<Marka> findAll() {
        return openSession(session -> session.createQuery("from Marka").list());
    }

    @Override
    public Marka findByID(Marka marka) {
        Marka rsl = openSession(session -> session.get(Marka.class, marka.getId()));
        if (rsl == null) {
            rsl = new Marka(0);
        }
        return rsl;
    }

    @Override
    public List<Marka> findByName(Marka marka) {
        error();
        return null;
    }

    @Override
    public Marka findByLoginPass(Marka marka) {
        error();
        return null;
    }

    @Override
    public Marka findByLogin(Marka marka) {
        error();
        return null;
    }
}
