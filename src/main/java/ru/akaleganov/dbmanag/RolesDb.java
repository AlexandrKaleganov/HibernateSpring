package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Roles;

import java.util.List;

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
        return openandCloseSession(session -> session.createQuery("from Roles ").list());
    }

    //мтод не раелизован
    @Override
    public Roles findByID(Roles roles) {
        error();
        return null;
    }

    //мтод не раелизован
    @Override
    public Roles findByName(Roles roles) {
        error();
        return null;
    }

    @Override
    public Roles findByLogin(Roles roles) {
        error();
        return null;
    }

}
