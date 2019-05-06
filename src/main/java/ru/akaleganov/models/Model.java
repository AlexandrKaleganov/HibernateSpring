package ru.akaleganov.models;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
public class Model {
    private int id;
    private String name;
    private Marka marka;

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

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

