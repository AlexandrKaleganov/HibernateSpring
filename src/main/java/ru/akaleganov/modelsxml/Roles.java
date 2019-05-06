package ru.akaleganov.modelsxml;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
public class Roles {
    private int id;
    private String role;

    public Roles(int id) {
        this.id = id;
    }

    public Roles() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" + "id=" + id + ", role='" + role + '\'' + '}';
    }
}
