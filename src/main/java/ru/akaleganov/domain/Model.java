package ru.akaleganov.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 **/
@Entity
@Table(name = "model")
public class Model extends AllModels {
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "mark_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Mark mark;

    public Model(int id) {
        super(id);
    }

    public Model() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Mark getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "Model{" + "id=" + super.getId() + ", name='" + name + '\'' + ", Mark='" + mark + '\'' + '}';
    }
}

