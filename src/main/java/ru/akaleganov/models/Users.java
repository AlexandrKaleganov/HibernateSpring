package ru.akaleganov.models;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
public class Users {
    private int id;
    private String name;
    private String login;
    private Roles roles;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", name='" + name + '\'' + ", login='"
                + login + '\'' + ", roles=" + roles + ", password='" + password + '\'' + '}';
    }
}
