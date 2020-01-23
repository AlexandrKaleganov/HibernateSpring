package ru.akaleganov.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.akaleganov.config.MyUserPrincipal;
import ru.akaleganov.domain.Users;
import ru.akaleganov.repository.UsersRepository;


/**
 * класс реализующй интерфейс {@link UserDetailsService}
 * как правило необходимо реализовать 1 метод который по логину пользователя найдёт пользователя в базе данных
 * и вернёт объет  {@link UserDetails}  который содержит геттеры и сеттеры для пользователя
 * @author Kaleganov Alexander
 * @version 0.0.1
 * @project HibernateSpring
 * @since 23 янв. 20
 */
@Service
public class UsersDetailServiceCustom implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(UsersDetailServiceCustom.class);
    @Autowired
    private UsersRepository usersRepository;

    /**
     *
     * @param s логин пользователя
     * @return {@link MyUserPrincipal}
     * @throws UsernameNotFoundException возможное исключение пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users = this.usersRepository.findByLogin(s).orElse(new Users());
        if (users.getLogin() == null) {
            throw new UsernameNotFoundException(s);
        }
        return new MyUserPrincipal(users);
    }
}
