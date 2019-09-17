package ru.akaleganov.modelsannot;


import javax.persistence.*;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 */
@Entity
@Table(name = "users")
public class Users extends AllModels {
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Roles roles;
    public Users(int id) {
        super(id);
    }

    public Users() {
        super();
    }

    public Users(String name, String login, Roles roles, String password) {
        this.name = name;
        this.login = login;
        this.roles = roles;
        this.password = password;
    }

    public Users(int id, String name, String login, Roles roles, String password) {
        super(id);
        this.name = name;
        this.login = login;
        this.roles = roles;
        this.password = password;
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
