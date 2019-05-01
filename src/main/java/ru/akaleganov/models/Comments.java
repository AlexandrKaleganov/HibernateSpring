package ru.akaleganov.models;

public class Comments {
    private int id;
    private User autor;
    private String comment;
    private Item item;

    @Override
    public String toString() {
        return "Comments{" + "id=" + id + ", autor=" + autor + ", comment='" + comment + '\'' + '}';
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
