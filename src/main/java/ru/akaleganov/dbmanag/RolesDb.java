package ru.akaleganov.dbmanag;

import net.bytebuddy.asm.Advice;
import ru.akaleganov.modelsannot.Roles;

import java.util.List;

public class RolesDb implements Store<Roles> {
    private static final RolesDb GETINSTANCE = new RolesDb();

    public static RolesDb GETINSTANCE() {
        return GETINSTANCE();
    }

    //мтод не раелизован
    @Override
    public Roles add(Roles roles) {
        return null;
    }

    //мтод не раелизован
    @Override
    public Roles delete(Roles roles) {
        return null;
    }

    //мтод не раелизован
    @Override
    public Roles edit(Roles roles) {
        return null;
    }

    @Override
    public List<Roles> findAll() {
        return openandCloseSession(session -> session.createQuery("from Roles ").list());
    }

    //мтод не раелизован
    @Override
    public Roles findByID(Roles roles) {
        return null;
    }

    //мтод не раелизован
    @Override
    public Roles findByName(Roles roles) {
        return null;
    }

}
