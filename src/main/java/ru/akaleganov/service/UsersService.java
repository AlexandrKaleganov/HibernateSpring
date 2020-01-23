package ru.akaleganov.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akaleganov.domain.Users;
import ru.akaleganov.repository.UsersRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
@Service
public class UsersService implements Store<Users> {
    private final UsersRepository usersRepository;

    private final Logger log = LoggerFactory.getLogger(UsersService.class);
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Users add(Users users) {
        Users test = this.findByLogin(users);
        if (test.getId() != 0L) {
            return users;
        } else {
            return this.usersRepository.save(this.setPasswordEncoder(users));
        }
    }

    @Override
    public Users delete(Users users) {
        this.usersRepository.delete(users);
        return users;
    }

    @Override
    public Users edit(Users users) {
        return this.usersRepository.save(this.setPasswordEncoder(users));
    }

    @Override
    public List<Users> findAll() {
        return (List<Users>) this.usersRepository.findAll();
    }

    @Override
    public Users findByID(Users users) {
        return this.usersRepository.findById(users.getId()).orElse(new Users(0));
    }

    @Override
    public List<Users> findByName(Users users) {
        String sql = "from Users where name = '" + users.getName() + "'";
        return this.usersRepository.findAllByName(users.getName());
    }

    @Override
    public Users findByLoginPass(Users users) {
        return this.usersRepository.findByLoginAndPassword(users.getLogin(),
                this.setPasswordEncoder(users).getPassword()).orElse(new Users());
    }

    @Override
    public Users findByLogin(Users users) {
        return this.usersRepository.findByLogin(users.getLogin()).orElse(new Users());
    }

    private Users setPasswordEncoder(Users users) {
        users.setPassword(this.passwordEncoder.encode(users.getPassword()));
        return users;
    }
}
