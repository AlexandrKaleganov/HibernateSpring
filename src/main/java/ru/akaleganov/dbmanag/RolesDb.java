package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Roles;

import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
public class RolesDb implements Store<Roles> {
    private static final RolesDb INSTANCE = new RolesDb();

    public static RolesDb getInstance() {
        return INSTANCE;
    }

    //мтод не раелизован
    @Override
    public Roles add(Roles roles) {
        error();
        return null;
    }

    //мтод не раелизован
    @Override
    public Roles delete(Roles roles) {
        error();
        return null;
    }

    //мтод не раелизован
    @Override
    public Roles edit(Roles roles) {
        error();
        return null;
    }

    @Override
    public List<Roles> findAll() {
        return openSession(session -> session.createQuery("from Roles ").list());
    }

    //мтод не раелизован
    @Override
    public Roles findByID(Roles roles) {
        error();
        return null;
    }

    //мтод не раелизован
    @Override
    public List<Roles> findByName(Roles roles) {
        error();
        return null;
    }

    @Override
    public Roles findByLogin(Roles roles) {
        error();
        return null;
    }

}
