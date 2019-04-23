package ru.akaleganov.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Objects;

public class Item {
    private Long id;
    private String descr;
    private Timestamp create;
    private boolean done;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String desc) {
        this.descr = desc;
    }

    @JsonFormat(pattern = "dd-MM-yyy HH:mm", timezone = "GMT+3")
    public Timestamp getCreate() {
        return create;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", desc='" + descr + '\''
                + ", create=" + create + ", done=" + done + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id && done == item.done
                && Objects.equals(descr, item.descr)
                && Objects.equals(create, item.create);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descr, create, done);
    }
}
