package ru.akaleganov.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.akaleganov.domain.Users;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


/**
 * Класс реализующий интерфейс UserDetails
 * содержит объект {@link Users} и геттеры и сеттеры для полей пользователя
 * @author Kaleganov Alexander
 * @version 0.0.1
 * @project HibernateSpring
 * @since 23 янв. 20
 */
public class MyUserPrincipal implements UserDetails {
    private final Logger log = LoggerFactory.getLogger(MyUserPrincipal.class);
    private final Users users;

    public MyUserPrincipal(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(this.users.getRoles());
    }

    @Override
    public String getPassword() {
        return this.users.getPassword();
    }

    @Override
    public String getUsername() {
        return this.users.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
