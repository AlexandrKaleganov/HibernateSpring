package ru.akaleganov.domain;

import javax.persistence.*;

/**
 *
 * @author Alexander Kalegano
 * @version 1
 * @since 15/05/19
 * абстрактный класс, в котором уже реализован идентификатор и конструктор
 * <br/>
 * <b>содержит поля:<b/>
 * @see AllModels#id
 */
@MappedSuperclass
abstract class AllModels implements Mod {
    /**
     * все объекты содержат id  уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public AllModels() {

    }

    AllModels(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
