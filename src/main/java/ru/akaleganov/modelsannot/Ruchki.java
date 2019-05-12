package ru.akaleganov.modelsannot;

public class Ruchki {
    private String name;
    private Pinal pinal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pinal getPinal() {
        return pinal;
    }

    public void setPinal(Pinal pinal) {
        this.pinal = pinal;
    }

    @Override
    public String toString() {
        return "Ruchki{" + "name='" + name + '\'' + '}';
    }
}
