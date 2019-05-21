package ru.akaleganov.modelsannot;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
@Entity
@Table(name = "announcement")
public class Announcement extends AllModels {
    @Column(name = "name")
    private String name;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "announcement")
    private Car car;
    @Column(name = "created_dat")
    private Timestamp created;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Users author;
    @Column(name = "done")
    private boolean done;

    public Announcement(int id) {
        super(id);
    }

    public Announcement() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @JsonFormat(pattern = "dd-MM-yyy HH:mm", timezone = "GMT+3")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Announcement{" + "id=" + super.getId() + ", name='" + name + "', car=" + car
                + ", created_dat=" + created + ", author=" + author
                + ", done=" + done + '}';
    }
}

