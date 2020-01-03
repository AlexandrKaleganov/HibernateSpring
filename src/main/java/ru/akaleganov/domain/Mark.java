package ru.akaleganov.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 **/
@Entity
@Table(name = "mark")
public class Mark extends AllModels {
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mark", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Model> models;

    public Mark(long id) {
        super(id);
    }

    public Mark() {
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
        return "Mark{" + "id=" + super.getId() + ", name='" + name + "\'}";
    }
}
