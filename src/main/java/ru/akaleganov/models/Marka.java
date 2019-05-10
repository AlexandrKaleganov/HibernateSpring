package ru.akaleganov.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
public class Marka {
    private int id;
    private String name;
    private List<Model> models;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Marka{" + "id=" + id + ", name='" + name + '\'' + models + '}';
    }
}
