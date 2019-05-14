package ru.akaleganov.modelsannot;

import javax.persistence.*;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 15/05/19
 * пропробую сделать абстрактный класс, в котором уже реализован идентификатор и конструктор
 * думаю будет работать, рипалил такое в одном видео уроке
 */
@MappedSuperclass
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
