package ru.akaleganov.modelsannot;

import javax.persistence.*;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
@Entity
@Table(name = "photo")
public class Photo extends AllModels {
    @Column(name = "photo")
    private byte[] photo;
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public Photo(int id) {
        super(id);
    }

    public Photo() {
        super();
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (byte b : photo) {
            temp.append(b);
        }
        return "Photo{" + "id=" + super.getId() + ", photo='" + temp + '\'' + '}';
    }
}
