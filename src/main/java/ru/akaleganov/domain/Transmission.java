package ru.akaleganov.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 **/
@Entity
@Table(name = "transmission")
public class Transmission extends AllModels {
    @Column(name = "name")
    private String name;

    public Transmission(int id) {
        super(id);
    }

    public Transmission() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Transmission{" + "id=" + super.getId() + ", name='" + name + '\'' + '}';
    }
}

