package ru.akaleganov.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
public class Announcement {
    private String name;
    private Car car;
    private Timestamp created;
    private Users users;
    private boolean done;

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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Announcement{" + "name='" + name + '\'' + ", car=" + car
                + ", created_dat=" + created + ", users=" + users
                + ", done=" + done + '}';
    }
}
