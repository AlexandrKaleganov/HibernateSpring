package ru.akaleganov.modelsannot;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
@Entity
@Table(name = "car")
public class Car extends AllModels {
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "marka_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Marka marka;
    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "transmission_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Transmission transmission;

    @Column(name = "yar")
    private int yar;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photo;
    @OneToOne
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;

    public Car(int id) {
        super(id);
    }

    public Car() {
        super();
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getYar() {
        return yar;
    }

    public void setYar(int yar) {
        this.yar = yar;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Car{" + "id=" + super.getId() + ", descrition=" + description + ", marka=" + marka + ", model=" + model + ", transmission="
                + transmission + ", yar=" + yar + ", photo=" + photo + '}';
    }
}

