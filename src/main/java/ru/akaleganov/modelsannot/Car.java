package ru.akaleganov.modelsannot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Класс автомобиль, создаётся уникальный объект для каждого объявления
 *
 * @author Kaleganov Aleander
 * @see Car#id
 * @see Car#photo
 * @see Car#announcement
 * @see Car#description
 * @see Car#model
 * @see Car#transmission
 * @see Car#yar
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 **/
@Entity
@Table(name = "car")
public class Car extends AllModels {
    /**
     * описание автомобиля
     */
    @Column(name = "description")
    private String description;
    /**
     * модель автомобиля
     */
    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Model model;
    /**
     * трансмиссия автомобиля
     */
    @ManyToOne
    @JoinColumn(name = "transmission_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Transmission transmission;
    /**
     * год выпуска автомобиля
     */
    @Column(name = "yar")
    private int yar;
    /**
     * лист фотографий которые относятся к данному автомобилю
     */
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<Photo> photo;
    /**
     * объявление к которому принадлежит автомобиль
     */
    @OneToOne
    @JoinColumn(name = "announcement_id")
    @JsonIgnore
    private Announcement announcement;

    /**
     * @param id все объекты содержат id  уникальный идентификатор
     */
    public Car(int id) {
        super(id);
    }

    public Car() {
        super();
    }

    /**
     * @return {@link Announcement}
     */
    public Announcement getAnnouncement() {
        return announcement;
    }

    /**
     * @param announcement {@link Announcement}
     */
    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    /**
     * @return {@link Model}
     */
    public Model getModel() {
        return model;
    }

    /**
     * @param model {@link Model}
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * @return {@link Transmission}
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * @param transmission {@link Transmission}
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * @return {@link Car#yar}
     */
    public int getYar() {
        return yar;
    }

    /**
     * @param yar год выпуска автомобиля
     */
    public void setYar(int yar) {
        this.yar = yar;
    }

    /**
     * @return {@link Photo}
     */
    public List<Photo> getPhoto() {
        return photo;
    }

    /**
     * @param photo {@link Photo}
     */
    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    /**
     * @return {@link Car#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description {@link Car#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + super.getId() + ", description=" + description + ", model=" + model + ", transmission="
                + transmission + ", yar=" + yar + ", photo=" + photo + '}';
    }
}

