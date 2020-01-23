package ru.akaleganov.domain;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 **/

@Entity
@Table(name = "roles")
public class Roles extends AllModels implements GrantedAuthority {
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

    public Roles(String role) {
        this.role = role;
    }

    public Roles(int id, String role) {
        super(id);
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" + "id=" + super.getId() + ", role='" + role + '\'' + '}';
    }

    @Override
    public String getAuthority() {
        return this.getRole();
    }
}
