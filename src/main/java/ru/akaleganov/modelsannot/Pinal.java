package ru.akaleganov.modelsannot;

import java.util.List;

public class Pinal {
    private String name;
    private List<Ruchki> ruchkis;

    public List<Ruchki> getRuchkis() {
        return ruchkis;
    }

    public void setRuchkis(List<Ruchki> ruchkis) {
        this.ruchkis = ruchkis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pinal{" + "name='" + name + '\'' + ", ruchkis=" + ruchkis + '}';
    }
}
