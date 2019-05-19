package ru.akaleganov.modelsannot;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import java.util.List;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
@Entity
@Table(name = "marka")
public class Marka extends AllModels {
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "marka", cascade = CascadeType.ALL)
    private List<Model> models;

    public Marka(int id) {
        super(id);
    }

    public Marka() {
        super();
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
        return "Marka{" + "id=" + super.getId() + ", name='" + name + '\'' + models + '}';
    }
}
