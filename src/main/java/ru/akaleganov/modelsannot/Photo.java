package ru.akaleganov.modelsannot;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
public class Photo {
    private int id;
    private byte[] photo;
    private Car car;

    public Photo(int id) {
        this.id = id;
    }

    public Photo() {
    }

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
        for (byte b : photo) {
            temp.append(b);
        }
        return "Photo{" + "id=" + id + ", photo='" + temp + '\'' + '}';
    }
}
