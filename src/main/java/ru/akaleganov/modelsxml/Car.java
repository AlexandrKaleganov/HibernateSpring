package ru.akaleganov.modelsxml;

import java.util.List;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
public class Car {
    private int id;
    private Marka marka;
    private Model model;
    private Transmission transmission;
    private int yar;
    private List<Photo> photo;
    private Announcement announcement;

    public Car(int id) {
        this.id = id;
    }

    public Car() {
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", marka=" + marka + ", model=" + model + ", transmission="
                + transmission + ", yar=" + yar + ", photo=" + photo + '}';
    }
}

