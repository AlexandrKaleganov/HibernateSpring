package ru.akaleganov.modelsannot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 **/
@Entity
@Table(name = "roles")
public class Roles extends AllModels {
    @Column(name = "role")
    private String role;

    public Roles(int id) {
        super(id);
    }

    public Roles() {
        super();
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" + "id=" + super.getId() + ", role='" + role + '\'' + '}';
    }
}
