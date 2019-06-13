package ru.akaleganov.modelsannot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Класс фотография
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 * @see Photo#photo
 * @see Photo#car
 *
 **/
@Entity
@Table(name = "photo")
public class Photo extends AllModels {
    /**
     * массив байт полученный из фотографии
     */
    @Column(name = "photo")
    private byte[] photo;
    /**
     * автомобиль, которому принадлежит фотография
     */
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private Car car;

    public Photo(int id) {
        super(id);
    }

    public Photo() {
        super();
    }

    /**
     *
     * @return {@link Photo#photo}
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo {@link Photo#photo}
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     *
     * @return {@link Car}
     */
    public Car getCar() {
        return car;
    }

    /**
     *
     * @param car {@link Car}
     */
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
