package ru.akaleganov.modelsannot;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * класс <b>объявлние</b>
 *
 * @author Kaleganov Aleander
 * @since 06/05//2019
 *
 * содержит в себе поля:
 * @see Announcement#name
 * @see Announcement#car
 * @see Announcement#created
 * @see Announcement#author
 * @see Announcement#done
 *
 **/
@Entity
@Table(name = "announcement")
public class Announcement extends AllModels {
    /** наименование объявления*/
    @Column(name = "name")
    private String name;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "announcement")
    /*объект автомобиль*/
    private Car car;
    @Column(name = "created_dat")
    /*объект трансмиссия*/
    private Timestamp created;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    /*автор объявления*/
    private Users author;
    @Column(name = "done")
    /*продано или нет*/
    private boolean done;

    public Announcement(int id) {
        super(id);
    }

    public Announcement() {
        super();
    }

    /**
     *
     * @return наименование объявления
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name изменение наименование объявления
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return {@link Car} получить объект автомобиль
     */
    public Car getCar() {
        return car;
    }

    /**
     *
     * @param car {@link Car} изменить объект автомобиль
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     *
     * @return получение даты и время  создания заявки
     */
    @JsonFormat(pattern = "dd-MM-yyy HH:mm", timezone = "GMT+3")
    public Timestamp getCreated() {
        return created;
    }

    /**
     *
     * @param created {@link Timestamp} изменение даты и время создания заявки
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     *
     * @return {@link Users} получить автора заявки
     */
    public Users getAuthor() {
        return author;
    }

    /**
     *
     * @param author {@link Users} зменить(задать) автора заявки
     */
    public void setAuthor(Users author) {
        this.author = author;
    }

    /**
     *
     * @return если автомобиль не продан вернёт false иначе true
     */
    public boolean isDone() {
        return done;
    }

    /**
     *
     * @param done изменить статус обявление если оно продано то придёт true
     */
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

