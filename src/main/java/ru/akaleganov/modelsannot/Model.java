package ru.akaleganov.modelsannot;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
public class Model {
    private int id;
    private String name;
    private Marka marka;

    public Model(int id) {
        this.id = id;
    }

    public Model() {
    }

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

    public void setMarka(Marka marka) {
        this.marka = marka;
    }
    public Marka getMarka() {
        return marka;
    }
    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

