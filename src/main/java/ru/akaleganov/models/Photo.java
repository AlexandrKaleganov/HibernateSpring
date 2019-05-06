package ru.akaleganov.models;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
public class Photo {
    private int id;
    private byte[] photo;
    private Car car;

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        for (int i = 0; i < photo.length; i++) {
            temp.append(photo[i]);
        }
        return "Photo{" + "id=" + id + ", photo='" + temp + '\'' + '}';
    }
}
