package ru.akaleganov.modelsannot;


/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users extends AllModels {
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;

    private Roles roles;
    private String password;

    public Users(int id) {
        super(id);
    }

    public Users() {
        super();
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
        return "Users{" + "id=" + super.getId() + ", name='" + name + '\'' + ", login='"
                + login + '\'' + ", roles=" + roles + ", password='" + password + '\'' + '}';
    }
}
