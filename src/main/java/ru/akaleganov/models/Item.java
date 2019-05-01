package ru.akaleganov.models;

import java.util.List;

public class Item {
    private int id;
    private User autor;
    private String descr;
    private List<Comments> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", autor=" + autor + ", descr='" + descr + '\'' + ", comments=" + comments + '}';
    }
}
