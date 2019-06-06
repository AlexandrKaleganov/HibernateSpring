package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Marka;
import ru.akaleganov.modelsannot.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelDb implements Store<Model> {
    private static final ModelDb INSTANCE = new ModelDb();

    public static ModelDb getInstance() {
        return INSTANCE;
    }
    @Override
    public Model add(Model model) {
        error();
        return null;
    }

    @Override
    public Model delete(Model model) {
        error();
        return null;
    }

    @Override
    public Model edit(Model model) {
        error();
        return null;
    }

    @Override
    public List<Model> findAll() {
        error();
        return null;
    }

    @Override
    public Model findByID(Model model) {
        error();
        return null;
    }

    @Override
    public List<Model> findByName(Model model) {
        error();
        return null;
    }
    public List<Model> findByMarkaid(Marka marka) {
        String sql = "from Model where marka_id = " + marka.getId();
        ArrayList<Model> res = (ArrayList<Model>) openSession(session -> session.createQuery(sql).list());
        if (res == null){
            res = new ArrayList<>();
        }
        return res;
    }
    @Override
    public Model findByLoginPass(Model model) {
        error();
        return null;
    }

    @Override
    public Model findByLogin(Model model) {
        error();
        return null;
    }
}
