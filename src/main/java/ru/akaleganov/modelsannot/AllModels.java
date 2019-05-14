package ru.akaleganov.modelsannot;

import javax.persistence.*;

@Entity
public abstract class AllModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public AllModels() {

    }

    public AllModels(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
