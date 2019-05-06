package ru.akaleganov.modelsxml;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
public class Transmission {
    private int id;
    private String name;

    public Transmission(int id) {
        this.id = id;
    }

    public Transmission() {
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

    @Override
    public String toString() {
        return "Transmission{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

